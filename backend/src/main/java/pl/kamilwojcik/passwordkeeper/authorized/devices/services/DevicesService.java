package pl.kamilwojcik.passwordkeeper.authorized.devices.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities.AuthorizedDevice;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities.UnauthorizedDevice;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.repositories.AuthorizedDeviceRepository;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.repositories.UnauthorizedDeviceRepository;
import pl.kamilwojcik.passwordkeeper.authorized.devices.dto.AuthorizedDeviceDTO;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.dto.CreateUnauthorizedDevice;
import pl.kamilwojcik.passwordkeeper.config.email.EmailService;
import pl.kamilwojcik.passwordkeeper.exceptions.request.NoRequiredHeaderException;
import pl.kamilwojcik.passwordkeeper.exceptions.resource.ResourceNotFoundException;
import pl.kamilwojcik.passwordkeeper.users.domain.repositories.UserRepository;
import pl.kamilwojcik.passwordkeeper.utils.RequestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class DevicesService
        implements
        AuthorizedDeviceRepoService,
        UnauthorizedDeviceService {

    private final AuthorizedDeviceRepository authorizedDeviceRepo;
    private final UnauthorizedDeviceRepository unauthorizedDeviceRepo;
    private final UserRepository userRepo;

    private final UserAgentService userAgentService;
    private final EmailService emailService;

    private final Long activationLinkExpireTime;

    public DevicesService(
            AuthorizedDeviceRepository authorizedDeviceRepo,
            UnauthorizedDeviceRepository unauthorizedDeviceRepo,
            UserRepository userRepo,
            UserAgentService userAgentService,
            EmailService emailService,
            @Value("${authorized-devices.activation-link-expire-time}") Long activationLinkExpireTime
    ) {
        this.authorizedDeviceRepo = authorizedDeviceRepo;
        this.unauthorizedDeviceRepo = unauthorizedDeviceRepo;
        this.userRepo = userRepo;
        this.userAgentService = userAgentService;
        this.emailService = emailService;
        this.activationLinkExpireTime = activationLinkExpireTime;
    }

    @Override
    public boolean authorizedDeviceExists(String ipAddress, String userAgentHeader, String username) {
        return authorizedDeviceRepo.existsByIpAddressAndUserAgentAndUser_Username(
                ipAddress,
                userAgentService.parseToStorageForm(userAgentHeader),
                username
        );
    }

    @Override
    public List<AuthorizedDeviceDTO> getAllAuthorizedDevices(String username) {
        return authorizedDeviceRepo
                .findByUser_Username(username)
                .stream()
                .map(device -> new AuthorizedDeviceDTO(
                        device.getPublicId(),
                        device.getIpAddress(),
                        device.getUserAgent(),
                        username
                )).toList();
    }

    @Override
    public void removeAuthorizedDevice(UUID devicePublicId, String username) {
        if(authorizedDeviceRepo.existsByPublicIdAndUser_Username(
                devicePublicId,
                username)
        ) {
            authorizedDeviceRepo.deleteByPublicId(devicePublicId);
        } else {
            throw new ResourceNotFoundException("Authorized device");
        }
    }



    @Override
    public void addNewUnauthorizedDevice(CreateUnauthorizedDevice unauthorizedDevice) {
        var user = userRepo.getByUsername(unauthorizedDevice.username())
                .orElseThrow(IllegalStateException::new);

        var entity = new UnauthorizedDevice(
                new Date(System.currentTimeMillis() + activationLinkExpireTime * 1000),
                unauthorizedDevice.ipAddress(),
                unauthorizedDevice.userAgentHeader(),
                user
        );

        entity = unauthorizedDeviceRepo.save(entity);

        sendDeviceAuthorizationEmail(user.getEmail(), entity);
    }

    @Override
    public void addNewUnauthorizedDevice(String username) {
        var request = RequestUtils.getRequest();
        var userAgentHeader = request.getHeader("User-Agent");
        if(userAgentHeader == null || userAgentHeader.isBlank()) {
            throw new NoRequiredHeaderException("User-Agent");
        }

        this.addNewUnauthorizedDevice(new CreateUnauthorizedDevice(
                request.getRemoteAddr(),
                userAgentService.parseToStorageForm(userAgentHeader),
                username
        ));
    }

    @Override
    public void authorizeDevice(String authorizationLink) {
        var unauthorizedDevice = unauthorizedDeviceRepo
                .findByAuthorizationLink(authorizationLink)
                .orElseThrow(ResourceNotFoundException::new);

        var entity = new AuthorizedDevice(
                unauthorizedDevice.getIpAddress(),
                unauthorizedDevice.getUserAgent(),
                unauthorizedDevice.getUser()
        );
        authorizedDeviceRepo.save(entity);
    }



    private void sendDeviceAuthorizationEmail(String userEmail, UnauthorizedDevice entity) {
        emailService.sendEmail("New device",
                "New device has been trying to your account.\n" +
                        "Device ip address: " + entity.getIpAddress() + ", " +
                        "device client: " + entity.getUserAgent() + "\n" +
                        "If it was you please click in link below in other case, " +
                        "change your creadentials as fast as polible." +
                        "New device authorization link: " + entity.getAuthorizationLink(),
                userEmail);
    }

}
