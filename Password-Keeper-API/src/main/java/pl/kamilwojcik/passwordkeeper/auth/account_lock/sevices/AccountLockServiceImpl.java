package pl.kamilwojcik.passwordkeeper.auth.account_lock.sevices;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.auth.account_lock.domain.AccountLock;
import pl.kamilwojcik.passwordkeeper.auth.account_lock.domain.AccountLockRepository;
import pl.kamilwojcik.passwordkeeper.auth.loggingEvent.domain.LoginEvent;
import pl.kamilwojcik.passwordkeeper.auth.loggingEvent.domain.LoginEventRepository;
import pl.kamilwojcik.passwordkeeper.users.domain.repositories.UserRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AccountLockServiceImpl implements AccountLockService {
    private final AccountLockRepository accountLockRepo;
    private final LoginEventRepository loginEventRepo;
    private final UserRepository userRepo;

    private final UserDetailsService userDetailsService;

    private final Integer MAX_UNSUCCESSFUL_LOGIN_ATTEMPTS = 5;
    private final Long LOGIN_CHECKING_TIME_MIN = 5L;
    private final Long LOCK_LENGTH_MIN = 10L;

    public AccountLockServiceImpl(AccountLockRepository accountLockRepo, LoginEventRepository loginEventRepo, UserRepository userRepo, UserDetailsService userDetailsService) {
        this.accountLockRepo = accountLockRepo;
        this.loginEventRepo = loginEventRepo;
        this.userRepo = userRepo;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void handleUnsuccessfulLogin(String username) {
        if (accountAlreadyLocked(username)) {
            return;
        }

        var now = new Date();
        if (getLastUnsuccessfulLogins(username, now).size() < MAX_UNSUCCESSFUL_LOGIN_ATTEMPTS) {
            return;
        }


        var entity = new AccountLock(
                now,
                new Date(now.getTime() + LOCK_LENGTH_MIN * 60_000),
                userRepo.findByUsername(username)
                        .orElseThrow(IllegalStateException::new)
        );
        accountLockRepo.saveAndFlush(entity);
    }

    private Boolean accountAlreadyLocked(String username) {
        return !userDetailsService.loadUserByUsername(username).isAccountNonLocked();
    }

    private List<LoginEvent> getLastUnsuccessfulLogins(String username, Date now) {
        return loginEventRepo
                .findAllByUser_UsernameAndLoginDateIsAfter(
                        username,
                        new Date(now.getTime() - LOGIN_CHECKING_TIME_MIN * 60_000)
                ).stream()
                .filter(loginEvent -> !loginEvent.getResult().wasSuccessful())
                .toList();
    }
}
