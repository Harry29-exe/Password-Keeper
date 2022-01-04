package pl.kamilwojcik.passwordkeeper.authorized.devices.services;

import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.authorized.devices.dto.AuthorizedDeviceDTO;

import java.util.List;
import java.util.UUID;

@Transactional
public interface AuthorizedDeviceRepoService {

    @PreAuthorize("@authFunctions.usernamesMatch(#username)")
    boolean authorizedDeviceExists(String ipAddress, String userAgentHeader, String username);

    @PreAuthorize("@authFunctions.usernamesMatch(#username)")
    List<AuthorizedDeviceDTO> getAllAuthorizedDevices(String username);

    @PreAuthorize("@authFunctions.usernamesMatch(#username)")
    void removeAuthorizedDevice(UUID devicePublicId, String username);

}
