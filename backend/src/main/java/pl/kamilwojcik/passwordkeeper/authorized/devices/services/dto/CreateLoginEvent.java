package pl.kamilwojcik.passwordkeeper.authorized.devices.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CreateLoginEvent {

    private Date loginDate;
    private UUID devicePublicId;
    private String username;

}
