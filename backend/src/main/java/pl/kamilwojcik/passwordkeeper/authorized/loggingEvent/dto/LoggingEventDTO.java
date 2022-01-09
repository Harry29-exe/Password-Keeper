package pl.kamilwojcik.passwordkeeper.authorized.loggingEvent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class LoggingEventDTO {

    private UUID publicId;
    private Date loginDate;
    private UUID devicePublicId;

}
