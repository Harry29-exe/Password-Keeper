package pl.kamilwojcik.passwordkeeper.auth.account_lock.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kamilwojcik.passwordkeeper.users.domain.entities.UserEntity;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class AccountLock {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date lockedFrom;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date lockedTo;

    @ManyToOne
    @JoinColumn
    private UserEntity user;

    public AccountLock(Date lockedFrom, Date lockedTo, UserEntity user) {
        this.lockedFrom = lockedFrom;
        this.lockedTo = lockedTo;
        this.user = user;
    }
}
