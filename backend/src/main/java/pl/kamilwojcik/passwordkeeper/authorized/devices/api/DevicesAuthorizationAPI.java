package pl.kamilwojcik.passwordkeeper.authorized.devices.api;

import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kamilwojcik.passwordkeeper.authorized.devices.dto.ClientDeviceDTO;

import java.util.List;

@Validated
@RequestMapping("device-authorization")
public interface DevicesAuthorizationAPI {

    @GetMapping
    List<ClientDeviceDTO> getAllAuthorizedDevices(Authentication auth);

    @GetMapping("{authorizationLink}")
    void authorizeDevice(@PathVariable String authorizationLink);

    @DeleteMapping("{device-public-id}")
    void deleteAuthorizedDevice(@PathVariable("device-public-id") String parameter);

}
