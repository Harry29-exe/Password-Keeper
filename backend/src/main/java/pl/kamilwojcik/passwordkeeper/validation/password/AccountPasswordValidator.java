package pl.kamilwojcik.passwordkeeper.validation.password;

import org.springframework.stereotype.Component;
import pl.kamilwojcik.passwordkeeper.validation.password.specyfication.PasswordSpecValidator;

@Component
public class AccountPasswordValidator {
    private final PasswordSpecValidator passwordSpecValidator;

    public AccountPasswordValidator(PasswordSpecValidator passwordSpecValidator) {
        this.passwordSpecValidator = passwordSpecValidator;
    }

    void validatePassword(String password) {
        passwordSpecValidator.passwordIsValid(password);
    }

}
