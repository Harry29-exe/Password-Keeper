package pl.kamilwojcik.passwordkeeper.auth.account_lock.sevices;

public interface AccountLockService {

    void handleUnsuccessfulLogin(String username);

}
