package pl.kamilwojcik.passwordkeeper.authorized.devices.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class AuthorizationLinkDTO {

    private String authorizationLink;
    private Date generatedAt;
    private Date activeUntil;

}
