package pl.kamilwojcik.passwordkeeper.authorized.loggingEvent.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities.ClientDevice;
import pl.kamilwojcik.passwordkeeper.users.domain.entities.UserEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "login_events")
public class LoginEvent {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, updatable = false, nullable = false)
    private UUID publicId = UUID.randomUUID();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date loginDate;

    @Column(updatable = false, nullable = false)
    private LoginEventResult result;

    @Column(nullable = false, updatable = false)
    private String ipAddress;

    @Column(length = 500)
    private String userAgent;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = true, updatable = false)
    private ClientDevice device;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private UserEntity user;

    public LoginEvent(Date loginDate, LoginEventResult result, ClientDevice device, String ipAddress, UserEntity user) {
        this.loginDate = loginDate;
        this.result = result;
        this.device = device;
        this.ipAddress = ipAddress;
        this.user = user;
    }

    public LoginEvent(Date loginDate, LoginEventResult result, String userAgent, String ipAddress, UserEntity user) {
        this.loginDate = loginDate;
        this.result = result;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.user = user;
    }

    public enum LoginEventResult {
        SUCCESS,
        FAILURE_GENERAL,
        FAILURE_CREDENTIALS,
        FAILURE_CLIENT_DEVICE
    }

}
