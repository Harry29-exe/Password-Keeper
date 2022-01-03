package pl.kamilwojcik.passwordkeeper.authorized.devices.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities.AuthorizedDevice;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities.UnauthorizedDevice;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.repositories.AuthorizedDeviceRepository;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.repositories.UnauthorizedDeviceRepository;
import pl.kamilwojcik.passwordkeeper.authorized.devices.dto.AuthorizedDeviceDTO;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.dto.CreateUnauthorizedDevice;
import pl.kamilwojcik.passwordkeeper.exceptions.resource.ResourceNotFoundException;
import pl.kamilwojcik.passwordkeeper.users.domain.repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DevicesRepoService
        implements
        AuthorizedDeviceRepoService,
        UnauthorizedDeviceRepoService {

    private final AuthorizedDeviceRepository authorizedDeviceRepo;
    private final UnauthorizedDeviceRepository unauthorizedDeviceRepo;
    private final UserRepository userRepo;

    private final Long activationLinkExpireTime;

    public DevicesRepoService(
            AuthorizedDeviceRepository authorizedDeviceRepo,
            UnauthorizedDeviceRepository unauthorizedDeviceRepo,
            UserRepository userRepo,
            @Value("${authorized-devices.activation-link-expire-time}")
            Long activationLinkExpireTime
    ) {
        this.authorizedDeviceRepo = authorizedDeviceRepo;
        this.unauthorizedDeviceRepo = unauthorizedDeviceRepo;
        this.userRepo = userRepo;
        this.activationLinkExpireTime = activationLinkExpireTime;
    }

    @Override
    public boolean authorizedDeviceExists(AuthorizedDeviceDTO authorizedDevice) {
        return authorizedDeviceRepo.existsByIpAddressAndClientNameAndUser_Username(
                authorizedDevice.getIpAddress(),
                authorizedDevice.getClientName(),
                authorizedDevice.getUsername()
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
                        device.getClientName(),
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
                unauthorizedDevice.clientName(),
                user
        );

        unauthorizedDeviceRepo.save(entity);
    }

    @Override
    public void authorizeDevice(String authorizationLink) {
        var unauthorizedDevice = unauthorizedDeviceRepo
                .findByAuthorizationLink(authorizationLink)
                .orElseThrow(ResourceNotFoundException::new);

        var entity = new AuthorizedDevice(
                unauthorizedDevice.getIpAddress(),
                unauthorizedDevice.getClientName(),
                unauthorizedDevice.getUser()
        );
        authorizedDeviceRepo.save(entity);
    }
}
