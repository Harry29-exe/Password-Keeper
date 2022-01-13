package pl.kamilwojcik.passwordkeeper.auth.authorized_devices.domain.entities;

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
@Table
public class DeviceAuthorizationLink {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID authorizationLink = UUID.randomUUID();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date generatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date activeUntil;

    @OneToOne(mappedBy = "authorizationLink")
    private ClientDevice clientDevice;

    public DeviceAuthorizationLink(Date generatedAt, Date activeUntil, ClientDevice clientDevice) {
        this.generatedAt = generatedAt;
        this.activeUntil = activeUntil;
        this.clientDevice = clientDevice;
    }
}
