package pl.kamilwojcik.passwordkeeper.passwords_storage.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kamilwojcik.passwordkeeper.users.domain.entities.UserEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FuturePasswordEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, updatable = false)
    private String encodedPassword;

    @ManyToOne
    @JoinColumn(name = "password_owner")
    private UserEntity user;

}
