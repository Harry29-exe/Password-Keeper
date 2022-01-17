package pl.kamilwojcik.passwordkeeper.auth.authorized_devices.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;
import pl.kamilwojcik.passwordkeeper.auth.loggingEvent.domain.LoginEvent.LoginEventResult;

import java.util.Date;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreateLoginEvent {

    private Date loginDate = new Date();
    private LoginEventResult result;
    private UUID devicePublicId;
    private String ipAddress;
    @Nullable
    private String userAgent;
    private String username;

    public CreateLoginEvent(LoginEventResult result, UUID devicePublicId, String ipAddress, String username) {
        this.result = result;
        this.devicePublicId = devicePublicId;
        this.username = username;
        this.ipAddress = ipAddress;
    }

    public CreateLoginEvent(LoginEventResult result, String userAgent, String ipAddress, String username) {
        this.result = result;
        this.username = username;
        this.userAgent = userAgent;
        this.ipAddress = ipAddress;
    }
}
