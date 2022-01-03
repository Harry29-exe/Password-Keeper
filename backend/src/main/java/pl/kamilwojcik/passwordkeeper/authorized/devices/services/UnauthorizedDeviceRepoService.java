package pl.kamilwojcik.passwordkeeper.authorized.devices.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.authorized.devices.dto.UnauthorizedDeviceDTO;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.dto.CreateUnauthorizedDevice;

@Transactional
public interface UnauthorizedDeviceRepoService {

    @PreAuthorize("@authFunctions.usernamesMatch(#unauthorizedDevice.username())")
    void addNewUnauthorizedDevice(
            CreateUnauthorizedDevice unauthorizedDevice);

    void authorizeDevice(String authorizationLink);

}
