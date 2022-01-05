package pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(unique = true)
    private UUID publicId = UUID.randomUUID();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date loginDate;

    @ManyToOne
    @JoinColumn(name = "device", nullable = false, updatable = false)
    private AuthorizedDevice device;

    public LoginEvent(Date loginDate, AuthorizedDevice device) {
        this.loginDate = loginDate;
        this.device = device;
    }
}
