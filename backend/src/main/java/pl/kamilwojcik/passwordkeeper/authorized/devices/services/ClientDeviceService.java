package pl.kamilwojcik.passwordkeeper.authorized.devices.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.authorized.devices.dto.ClientDeviceDTO;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.dto.CreateClientDevice;

import java.util.List;
import java.util.UUID;

@Transactional
public interface ClientDeviceService {

    @PreAuthorize("@authFunctions.usernamesMatch(#unauthorizedDevice.username())")
    void addNewClientDevice(
            CreateClientDevice unauthorizedDevice);

    void addNewClientDeviceBasedOnRequest(String username);

    void authorizeDevice(String authorizationLink);

    boolean authorizedDeviceExists(String ipAddress, String userAgentHeader, String username);

    @PreAuthorize("@authFunctions.usernamesMatch(#username)")
    List<ClientDeviceDTO> getAllAuthorizedDevices(String username);

    @PreAuthorize("@authFunctions.usernamesMatch(#username)")
    void removeAuthorizedDevice(UUID devicePublicId, String username);

}
