package pl.kamilwojcik.passwordkeeper.authorized.devices.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ClientDeviceDTO {

    private UUID publicId;

    private String ipAddress;

    private String userAgent;

    private Boolean isAuthorized;

}
