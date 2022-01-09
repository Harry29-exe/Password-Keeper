package pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kamilwojcik.passwordkeeper.authorized.loggingEvent.domain.LoginEvent;
import pl.kamilwojcik.passwordkeeper.users.domain.entities.UserEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "client_devices", uniqueConstraints = {
        @UniqueConstraint(
                name = "authorized_device",
                columnNames = {"ip_address", "client_name", "user_id"}
        )
}
)
public class ClientDevice {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    private UUID publicId = UUID.randomUUID();

    @Column(nullable = false, updatable = false, name = "ip_address")
    private String ipAddress;

    @Column(nullable = false, updatable = false, name = "client_name")
    private String userAgent;

    @Column(nullable = false)
    private Boolean isAuthorized = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authorization_link_id", unique = true)
    private DeviceAuthorizationLink authorizationLink;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "device")
    private List<LoginEvent> loginEvents;

    public ClientDevice(String ipAddress, String userAgent, Long activationLinkExpireTimeInSec, UserEntity user) {
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.user = user;

        var now = new Date();
        this.authorizationLink = new DeviceAuthorizationLink(
                now,
                new Date(now.getTime() + activationLinkExpireTimeInSec * 1000),
                this
        );
    }


}
