package pl.kamilwojcik.passwordkeeper.authorized.devices.api;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import pl.kamilwojcik.passwordkeeper.authorized.devices.dto.ClientDeviceDTO;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.ClientDeviceService;

import java.util.List;

@RestController
public class DevicesAuthorizationAPIImpl implements DevicesAuthorizationAPI {
    private final ClientDeviceService clientDeviceService;

    public DevicesAuthorizationAPIImpl(ClientDeviceService clientDeviceService) {
        this.clientDeviceService = clientDeviceService;
    }

    @Override
    public List<ClientDeviceDTO> getAllAuthorizedDevices(Authentication auth) {
        return null;
    }

    @Override
    public void authorizeDevice(String authorizationLink) {

    }

    @Override
    public void deleteAuthorizedDevice(String parameter) {

    }
}
