package pl.kamilwojcik.passwordkeeper.auth.loggingEvent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class LoginEventDTO {

    private UUID publicId;
    private Date loginDate;
    private String ipAddress;
    private String userAgent;
    private UUID devicePublicId;

}
