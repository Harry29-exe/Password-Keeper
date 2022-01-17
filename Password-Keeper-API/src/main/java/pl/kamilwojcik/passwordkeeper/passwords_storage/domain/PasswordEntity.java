package pl.kamilwojcik.passwordkeeper.passwords_storage.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kamilwojcik.passwordkeeper.users.domain.entities.UserEntity;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "passwords_storage",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"password_name", "password_owner"})
        })
public class PasswordEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "password_name", nullable = false, updatable = false)
    private String passwordName;

    @Column(name = "password_url", nullable = true)
    private String passwordUrl;

    @Lob
    @Column(name = "encoded_password", nullable = false, updatable = false)
    private String encryptedPassword;

    @Lob
    @Column(nullable = false, updatable = false)
    private byte[] iv;

    @Lob
    @Column(nullable = false, updatable = false)
    private byte[] salt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "password_owner", nullable = false, updatable = false)
    private UserEntity user;

    public PasswordEntity(String encryptedPassword, String passwordName, String passwordUrl, byte[] iv, byte[] salt, UserEntity user) {
        this.encryptedPassword = encryptedPassword;
        this.passwordName = passwordName;
        this.passwordUrl = passwordUrl;
        this.iv = iv;
        this.salt = salt;
        this.user = user;
    }
}
