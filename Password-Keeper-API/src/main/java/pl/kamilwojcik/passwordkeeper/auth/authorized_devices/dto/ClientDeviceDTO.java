package pl.kamilwojcik.passwordkeeper.auth.authorized_devices.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ClientDeviceDTO {

    private UUID publicId;

    private String ipAddress;

    private String userAgent;

    private String username;

    private Boolean isAuthorized;

}
