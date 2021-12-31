package pl.kamilwojcik.passwordkeeper.authorized.devices.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UnauthorizedDeviceDTO {

    private String ipAddress;

    private String clientName;

    private String username;

}
