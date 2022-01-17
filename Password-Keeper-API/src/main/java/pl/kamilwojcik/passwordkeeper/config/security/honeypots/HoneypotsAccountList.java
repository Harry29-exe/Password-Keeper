package pl.kamilwojcik.passwordkeeper.config.security.honeypots;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class HoneypotsAccountList {
    private final Set<String> honeypotAccountsUsernames = new HashSet<>();

    public HoneypotsAccountList() {
        honeypotAccountsUsernames.add("Admin");
        honeypotAccountsUsernames.add("User1");
        honeypotAccountsUsernames.add("Bob");
    }

    public boolean isHoneypot(Authentication auth) {
        return honeypotAccountsUsernames.contains(auth.getName());
    }

}
