package pl.kamilwojcik.passwordkeeper.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.kamilwojcik.passwordkeeper.users.domain.repositories.UserRepository;

@Component
public class AuthFunctions {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthFunctions(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean usernamesMatch(String username) {
        try {
            //todo
//            userValidator.validateUsername(username);
        } catch (Exception ex) {
            return false;
        }

        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            return false;
        }
        if(!(auth instanceof UsernamePasswordAuthenticationToken)) {
            throw new IllegalArgumentException("Authentication other than UsernamePasswordAuthenticationToken are not supported");
        }


        return auth.getName().equals(username);
    }

    public boolean storagePasswordMatch(String storagePassword, String username) {
        if(storagePassword == null || storagePassword.isBlank() ||
                username == null || username.isBlank()) {
            return false;
        }
        var user = userRepo.findByUsername(username)
                .orElseThrow();

        var inputEncryptedPassword = passwordEncoder.encode(storagePassword);
        return inputEncryptedPassword.equals(user.getStoragePassword());
    }

}
