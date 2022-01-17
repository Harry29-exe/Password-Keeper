package pl.kamilwojcik.passwordkeeper.auth.authorized_devices.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.auth.authorized_devices.dto.ClientDeviceDTO;
import pl.kamilwojcik.passwordkeeper.auth.authorized_devices.services.dto.CreateClientDevice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
public interface ClientDeviceService {

    @PreAuthorize("@authFunctions.usernamesMatch(#unauthorizedDevice.username())")
    void createNewDeviceAndSendEmail(
            CreateClientDevice unauthorizedDevice);

    /**
     * based on information given in request creates new device and sends authorization email
     * or if it already exists and authorization link is expired recreates authorization link and
     * resends authorization email
     *
     * @param username username of user that new device should be created for
     */
    void addNewDeviceAuthorizationRequest(String username);

    /**
     * authorize device based on unique long and random authorizationLink
     *
     * @param authorizationLink - link which is sent to user's email
     */
    void authorizeDevice(String authorizationLink);

    Optional<ClientDeviceDTO> findByDeviceConstraint(String ipAddress, String userAgentHeader, String username);

    @PreAuthorize("isAuthenticated()")
    Optional<ClientDeviceDTO> getCurrentDevice();

    Optional<ClientDeviceDTO> getCurrentDevice(String username);

    @PreAuthorize("@authFunctions.usernamesMatch(#username)")
    List<ClientDeviceDTO> getAllAuthorizedDevices(String username);

    @PreAuthorize("@authFunctions.usernamesMatch(#username)")
    void removeAuthorizedDevice(UUID devicePublicId, String username);

}
