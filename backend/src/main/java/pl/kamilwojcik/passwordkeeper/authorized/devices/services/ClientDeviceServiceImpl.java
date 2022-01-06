package pl.kamilwojcik.passwordkeeper.authorized.devices.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities.ClientDevice;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.repositories.AuthorizationLinkRepository;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.repositories.ClientDeviceRepository;
import pl.kamilwojcik.passwordkeeper.authorized.devices.dto.ClientDeviceDTO;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.dto.CreateClientDevice;
import pl.kamilwojcik.passwordkeeper.config.email.EmailService;
import pl.kamilwojcik.passwordkeeper.exceptions.request.NoRequiredHeaderException;
import pl.kamilwojcik.passwordkeeper.exceptions.resource.ResourceNotExistException;
import pl.kamilwojcik.passwordkeeper.users.domain.repositories.UserRepository;
import pl.kamilwojcik.passwordkeeper.utils.RequestUtils;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ClientDeviceServiceImpl
        implements
        ClientDeviceService {

    private final ClientDeviceRepository clientDeviceRepo;
    private final AuthorizationLinkRepository authLinkRepo;
    private final UserRepository userRepo;

    private final UserAgentService userAgentService;
    private final EmailService emailService;

    private final Long activationLinkExpireTimeInSec;

    public ClientDeviceServiceImpl(
            ClientDeviceRepository clientDeviceRepo,
            AuthorizationLinkRepository authLinkRepo, UserRepository userRepo,
            UserAgentService userAgentService,
            EmailService emailService,
            @Value("${authorized-devices.activation-link-expire-time-sec}") Long activationLinkExpireTimeInSec
    ) {
        this.clientDeviceRepo = clientDeviceRepo;
        this.authLinkRepo = authLinkRepo;
        this.userRepo = userRepo;
        this.userAgentService = userAgentService;
        this.emailService = emailService;
        this.activationLinkExpireTimeInSec = activationLinkExpireTimeInSec;
    }

    @Override
    public void addClientDevice(CreateClientDevice unauthorizedDevice) {
        var user = userRepo.getByUsername(unauthorizedDevice.username())
                .orElseThrow(IllegalStateException::new);

        var userAgent = userAgentService.parseToStorageForm(
                unauthorizedDevice.userAgentHeader()
        );

        var entity = new ClientDevice(
                unauthorizedDevice.ipAddress(),
                userAgent,
                activationLinkExpireTimeInSec,
                user
        );

        entity = clientDeviceRepo.save(entity);

        sendDeviceAuthorizationEmail(user.getEmail(), entity);
    }

    @Override
    public void addClientDevice(String username) {
        var request = RequestUtils.getRequest();
        var userAgentHeader = request.getHeader("User-Agent");
        if (userAgentHeader == null || userAgentHeader.isBlank()) {
            throw new NoRequiredHeaderException("User-Agent");
        }

        this.addClientDevice(new CreateClientDevice(
                request.getRemoteAddr(),
                userAgentHeader,
                username
        ));
    }

    @Override
    public List<ClientDeviceDTO> getAllAuthorizedDevices(String username) {
        return clientDeviceRepo
                .findByUser_Username(username)
                .stream()
                .map(device -> new ClientDeviceDTO(
                        device.getPublicId(),
                        device.getIpAddress(),
                        device.getUserAgent(),
                        device.getIsAuthorized()
                )).toList();
    }

    @Override
    public boolean authorizedDeviceExists(String ipAddress, String userAgentHeader, String username) {
        return clientDeviceRepo.existsByIpAddressAndUserAgentAndUser_Username(
                ipAddress,
                userAgentService.parseToStorageForm(userAgentHeader),
                username
        );
    }

    @Override
    public void authorizeDevice(String authorizationLink) {
        var authLink = authLinkRepo
                .findByAuthorizationLink(UUID.fromString(authorizationLink))
                .orElseThrow(ResourceNotExistException::new);

        var device = authLink.getClientDevice();
        device.setIsAuthorized(true);
        device.setAuthorizationLink(null);
        clientDeviceRepo.save(device);
        authLinkRepo.delete(authLink);
    }

    @Override
    public void removeAuthorizedDevice(UUID devicePublicId, String username) {
        if (clientDeviceRepo.existsByPublicIdAndUser_Username(
                devicePublicId,
                username)
        ) {
            clientDeviceRepo.deleteByPublicId(devicePublicId);
        } else {
            throw new ResourceNotExistException("Authorized device");
        }
    }


    private void sendDeviceAuthorizationEmail(String userEmail, ClientDevice clientDevice) {
        emailService.sendEmail("New device",
                "New device has been trying to your account.\n" +
                        "Device ip address: " + clientDevice.getIpAddress() + ", " +
                        "device client: " + clientDevice.getUserAgent() + "\n" +
                        "If it was you please click in link below in other case, " +
                        "change your credentials as fast as possible.\n" +
                        "New device authorization link: " + clientDevice.getAuthorizationLink().getAuthorizationLink(),
                userEmail);
    }

}
