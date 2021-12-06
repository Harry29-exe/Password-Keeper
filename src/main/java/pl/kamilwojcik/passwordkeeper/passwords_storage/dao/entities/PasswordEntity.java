package pl.kamilwojcik.passwordkeeper.passwords_storage.dao.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kamilwojcik.passwordkeeper.users.dao.entities.UserEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PasswordEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String passwordName;

    @Column
    private String encodedPassword;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    private UserEntity user;

}
