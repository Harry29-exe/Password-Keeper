package pl.kamilwojcik.passwordkeeper.auth.authorized_devices.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kamilwojcik.passwordkeeper.auth.authorized_devices.dto.ClientDeviceDTO;
import pl.kamilwojcik.passwordkeeper.config.DefaultCors;

import java.util.List;

@Validated
@DefaultCors
@RequestMapping("device-authorization")
public interface DevicesAuthorizationAPI {

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    List<ClientDeviceDTO> getAllAuthorizedDevices(Authentication auth);

    @GetMapping("{authorizationLink}")
    void authorizeDevice(@PathVariable String authorizationLink);

    @DeleteMapping("{device-public-id}")
    @PreAuthorize("isAuthenticated()")
    void deleteAuthorizedDevice(@PathVariable("device-public-id") String devicePublicId, Authentication auth);

}
