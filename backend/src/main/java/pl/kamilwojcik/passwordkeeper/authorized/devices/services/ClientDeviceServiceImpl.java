package pl.kamilwojcik.passwordkeeper.authorized.devices.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities.ClientDevice;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities.DeviceAuthorizationLink;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.repositories.AuthorizationLinkRepository;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.repositories.ClientDeviceRepository;
import pl.kamilwojcik.passwordkeeper.authorized.devices.dto.ClientDeviceDTO;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.dto.CreateClientDevice;
import pl.kamilwojcik.passwordkeeper.config.consts.BackendAddress;
import pl.kamilwojcik.passwordkeeper.config.email.EmailService;
import pl.kamilwojcik.passwordkeeper.exceptions.email.EmailAlreadyHasBeenSentException;
import pl.kamilwojcik.passwordkeeper.exceptions.request.NoRequiredHeaderException;
import pl.kamilwojcik.passwordkeeper.exceptions.resource.ResourceAlreadyExistException;
import pl.kamilwojcik.passwordkeeper.exceptions.resource.ResourceNotExistException;
import pl.kamilwojcik.passwordkeeper.users.domain.repositories.UserRepository;
import pl.kamilwojcik.passwordkeeper.utils.RequestUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    public void createNewDeviceAndSendEmail(CreateClientDevice unauthorizedDevice) {
        var userAgent = userAgentService.parseToStorageForm(
                unauthorizedDevice.userAgentHeader()
        );

        if (clientDeviceRepo
                .existsByIpAddressAndUserAgentAndUser_UsernameAndIsAuthorizedIsTrue(
                        unauthorizedDevice.ipAddress(),
                        userAgent,
                        unauthorizedDevice.username())
        ) {
            throw new ResourceAlreadyExistException();
        }


        var user = userRepo.getByUsername(unauthorizedDevice.username())
                .orElseThrow(IllegalStateException::new);


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
    public void addNewDeviceAuthorizationRequest(String username) {
        var request = RequestUtils.getCurrentRequest();
        var userAgentHeader = request.getHeader("User-Agent");
        if (userAgentHeader == null || userAgentHeader.isBlank()) {
            throw new NoRequiredHeaderException("User-Agent");
        }
        String ipAddress = request.getRemoteAddr();
        String userAgent = userAgentService.parseToStorageForm(userAgentHeader);

        var existingDevice = clientDeviceRepo.findByIpAddressAndUserAgentAndUser_Username(
                ipAddress,
                userAgent,
                username
        );

        if (existingDevice.isEmpty()) {
            this.createNewDeviceAndSendEmail(new CreateClientDevice(
                    ipAddress,
                    userAgentHeader,
                    username
            ));

            return;
        }

        recreateAuthorizationLinkAndSendEmila(existingDevice.get());
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
                        username,
                        device.getIsAuthorized()
                )).toList();
    }

    @Override
    public Optional<ClientDeviceDTO> findByDeviceConstraint(String ipAddress, String userAgentHeader, String username) {
        var userAgent = userAgentService.parseToStorageForm(userAgentHeader);

        var deviceWrapper = clientDeviceRepo.
                findByIpAddressAndUserAgentAndUser_Username(ipAddress, userAgent, username);
        if (deviceWrapper.isEmpty()) {
            return Optional.empty();
        }

        var device = deviceWrapper.get();
        return Optional.of(new ClientDeviceDTO(
                device.getPublicId(),
                device.getIpAddress(),
                device.getUserAgent(),
                username,
                device.getIsAuthorized()
        ));
    }

    @Override
    public Optional<ClientDeviceDTO> getCurrentDevice() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return Optional.empty();
        }

        return getCurrentDevice(auth.getName());
    }

    @Override
    public Optional<ClientDeviceDTO> getCurrentDevice(String username) {
        var request = RequestUtils.getCurrentRequest();
        var userAgentHeader = request.getHeader("User-Agent");
        if (userAgentHeader == null) {
            return Optional.empty();
        }

        var ipAddress = request.getRemoteAddr();
        var userAgent = userAgentService.parseToStorageForm(userAgentHeader);

        return clientDeviceRepo.findByIpAddressAndUserAgentAndUser_Username(
                ipAddress,
                userAgent,
                username
        ).map(this::mapEntityToDTO);
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

    private void recreateAuthorizationLinkAndSendEmila(ClientDevice clientDevice) {
        var link = clientDevice.getAuthorizationLink();
        var now = new Date();
        if (now.after(link.getActiveUntil())) {
            var newLink = new DeviceAuthorizationLink(
                    now,
                    new Date(now.getTime() + activationLinkExpireTimeInSec * 1000),
                    clientDevice
            );
            clientDevice.setAuthorizationLink(newLink);
            clientDeviceRepo.saveAndFlush(clientDevice);

            sendDeviceAuthorizationEmail(clientDevice.getUser().getEmail(), clientDevice);
        } else {
            throw new EmailAlreadyHasBeenSentException();
        }
    }

    private void sendDeviceAuthorizationEmail(String userEmail, ClientDevice clientDevice) {
        emailService.sendEmail("New device",
                "New device has been trying to your account.\n" +
                        "Device ip address: " + clientDevice.getIpAddress() + ", " +
                        "device client: " + clientDevice.getUserAgent() + "\n" +
                        "If it was you please click in link below in other case, " +
                        "change your credentials as fast as possible.\n" +
                        "New device authorization link: " +
                        BackendAddress.BACKEND_ADDRESS +
                        "/device-authorization/" +
                        clientDevice.getAuthorizationLink().getAuthorizationLink(),
                userEmail);
    }

    private ClientDeviceDTO mapEntityToDTO(ClientDevice clientDevice) {
        return new ClientDeviceDTO(
                clientDevice.getPublicId(),
                clientDevice.getIpAddress(),
                clientDevice.getUserAgent(),
                clientDevice.getUser().getUsername(),
                clientDevice.getIsAuthorized()
        );
    }

}
