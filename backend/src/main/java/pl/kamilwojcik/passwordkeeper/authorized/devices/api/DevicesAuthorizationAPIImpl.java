package pl.kamilwojcik.passwordkeeper.authorized.devices.api;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import pl.kamilwojcik.passwordkeeper.authorized.devices.dto.AuthorizedDeviceDTO;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.AuthorizedDeviceRepoService;

import java.util.List;

@RestController
public class DevicesAuthorizationAPIImpl implements DevicesAuthorizationAPI {
    private final AuthorizedDeviceRepoService authorizedDeviceRepoService;

    public DevicesAuthorizationAPIImpl(AuthorizedDeviceRepoService authorizedDeviceRepoService) {
        this.authorizedDeviceRepoService = authorizedDeviceRepoService;
    }

    @Override
    public List<AuthorizedDeviceDTO> getAllAuthorizedDevices(Authentication auth) {
        return null;
    }

    @Override
    public void authorizeDevice(String authorizationLink) {

    }

    @Override
    public void deleteAuthorizedDevice(String parameter) {

    }
}
