package pl.kamilwojcik.passwordkeeper.users.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kamilwojcik.passwordkeeper.passwords_storage.domain.entities.PasswordEntity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, updatable = false, nullable = false)
    private UUID publicId;

    @Column(unique = true, nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String storagePassword;

    @OneToMany(mappedBy = "user")
    private List<PasswordEntity> passwordsInStorage;

    public UserEntity(String username, String password, String storagePassword) {
        this.username = username;
        this.password = password;
        this.storagePassword = storagePassword;
    }

    @PrePersist
    public void generatePublicId() {
        this.publicId = UUID.randomUUID();
    }

}