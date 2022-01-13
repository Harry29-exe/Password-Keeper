package pl.kamilwojcik.passwordkeeper.auth.account_lock.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface AccountLockRepository extends JpaRepository<AccountLock, Long> {

    Optional<AccountLock> findByUser_UsernameAndLockedToAfter(String username, Date lockedToAfter);

}
