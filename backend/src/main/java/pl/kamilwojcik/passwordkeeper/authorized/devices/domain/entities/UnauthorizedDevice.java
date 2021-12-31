package pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kamilwojcik.passwordkeeper.users.domain.entities.UserEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "unauthorized_devices")
public class UnauthorizedDevice {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, updatable = false, unique = true, length = 100)
    private String authorizationLink;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date linkExpirationDate;

    @Column(nullable = false, updatable = false)
    private String ipAddress;

    @Column(nullable = false, updatable = false)
    private String clientName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
