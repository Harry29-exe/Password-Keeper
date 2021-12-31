package pl.kamilwojcik.passwordkeeper.authorized.devices.services;

import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.authorized.devices.dto.UnauthorizedDeviceDTO;

@Transactional
public interface UnauthorizedDeviceService {

    void addNewUnauthorizedDevice(UnauthorizedDeviceDTO unauthorizedDevice);

    void authorizeDevice(String authorizationLink);

}
