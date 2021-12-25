package pl.kamilwojcik.passwordkeeper.config.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.kamilwojcik.passwordkeeper.validators.UserValidator;

@Component
public class AuthorizationUtils {
    private final UserValidator userValidator;

    public AuthorizationUtils(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    public boolean usernamesMatch(String username) {
        try {
            userValidator.validateUsername(username);
        } catch (Exception ex) {
            return false;
        }

        var auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null) {
            return false;
        }

        return auth.getName().equals(username);
    }

}
