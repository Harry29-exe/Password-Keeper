package pl.kamilwojcik.passwordkeeper.authorized.devices.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.dto.CreateUnauthorizedDevice;

@Transactional
public interface UnauthorizedDeviceService {

    @PreAuthorize("@authFunctions.usernamesMatch(#unauthorizedDevice.username())")
    void addNewUnauthorizedDevice(
            CreateUnauthorizedDevice unauthorizedDevice);

    @PreAuthorize("@authFunctions.usernamesMatch(#username)")
    void addNewUnauthorizedDevice(String username);

    void authorizeDevice(String authorizationLink);

}
