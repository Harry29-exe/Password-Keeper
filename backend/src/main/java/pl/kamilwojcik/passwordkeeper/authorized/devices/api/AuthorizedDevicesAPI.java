package pl.kamilwojcik.passwordkeeper.authorized.devices.api;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kamilwojcik.passwordkeeper.authorized.devices.dto.AuthorizedDeviceDTO;

import java.util.List;

@RequestMapping("authorized-devices")
public interface AuthorizedDevicesAPI {

    @GetMapping
    List<AuthorizedDeviceDTO> getAllAuthorizedDevices(Authentication auth);

    @DeleteMapping("{device-public-id}")
    void deleteAuthorizedDevice(@PathVariable("device-public-id") String parameter);

}
