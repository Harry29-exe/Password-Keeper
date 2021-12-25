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

    @Column(name = "password_name", nullable = false)
    private String passwordName;

    @Column(name = "encoded_password", nullable = false)
    private String encodedPassword;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "password_owner", nullable = false, updatable = false)
    private UserEntity user;

}
