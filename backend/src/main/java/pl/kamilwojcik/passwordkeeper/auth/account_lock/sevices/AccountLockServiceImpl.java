package pl.kamilwojcik.passwordkeeper.auth.account_lock.sevices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.auth.account_lock.domain.AccountLock;
import pl.kamilwojcik.passwordkeeper.auth.account_lock.domain.AccountLockRepository;
import pl.kamilwojcik.passwordkeeper.auth.loggingEvent.domain.LoginEventRepository;
import pl.kamilwojcik.passwordkeeper.users.domain.repositories.UserRepository;

import java.util.Date;

@Service
@Transactional
public class AccountLockServiceImpl implements AccountLockService {
    private final AccountLockRepository accountLockRepository;
    private final LoginEventRepository loginEventRepo;
    private final UserRepository userRepo;

    private final Integer MAX_UNSUCCESSFUL_LOGIN_ATTEMPTS = 5;
    private final Long LOGIN_CHECKING_TIME_MIN = 5L;
    private final Long LOCK_LENGTH_MIN = 10L;

    public AccountLockServiceImpl(AccountLockRepository accountLockRepository, LoginEventRepository loginEventRepo, UserRepository userRepo) {
        this.accountLockRepository = accountLockRepository;
        this.loginEventRepo = loginEventRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void handleUnsuccessfulLogin(String username) {
        var now = new Date();

        var lastUnsuccessfulLogins = loginEventRepo
                .findAllByUser_UsernameAndLoginDateIsAfter(
                        username,
                        new Date(now.getTime() - LOGIN_CHECKING_TIME_MIN * 60_000)
                ).stream()
                .filter(loginEvent -> !loginEvent.getResult().wasSuccessful())
                .toList();

        if (lastUnsuccessfulLogins.size() < MAX_UNSUCCESSFUL_LOGIN_ATTEMPTS) {
            return;
        }

        var user = userRepo.findByUsername(username)
                .orElseThrow(IllegalStateException::new);

        var entity = new AccountLock(
                now,
                new Date(now.getTime() + LOCK_LENGTH_MIN * 60_000),
                user
        );
        accountLockRepository.saveAndFlush(entity);
    }
}
