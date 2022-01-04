package pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kamilwojcik.passwordkeeper.users.domain.entities.UserEntity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "authorized_devices", uniqueConstraints = {
        @UniqueConstraint(
                name = "authorized_device",
                columnNames = {"ip_address", "client_name", "user_id"}
        )
}
)

public class AuthorizedDevice {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    private UUID publicId = UUID.randomUUID();

    @Column(nullable = false, updatable = false, name = "ip_address")
    private String ipAddress;

    @Column(nullable = false, updatable = false, name = "client_name")
    private String userAgent;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "device")
    private List<LoginEvent> loginEvents;

    public AuthorizedDevice(String ipAddress, String userAgent, UserEntity user) {
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.user = user;
    }
}
