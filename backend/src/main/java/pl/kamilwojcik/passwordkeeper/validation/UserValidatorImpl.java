package pl.kamilwojcik.passwordkeeper.validation;

import org.springframework.stereotype.Component;
import pl.kamilwojcik.passwordkeeper.validation.password.specyfication.PasswordSpecValidator;

import javax.validation.ValidationException;
import java.util.List;

@Component
public class UserValidatorImpl implements UserValidator {
    private final PasswordSpecValidator passwordSpecValidator;

    private final List<Character> additionalAllowedChars = List.of('_', '@', '$');

    public UserValidatorImpl(PasswordSpecValidator passwordSpecValidator) {
        this.passwordSpecValidator = passwordSpecValidator;
    }

    @Override
    public void validateUsername(String username) {
        for (var c : username.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                throw new ValidationException("Username contains illegal signs which are nor character nor digit");
            }
        }
    }

    @Override
    public void validatePassword(String password) {
        for (var c : password.toCharArray()) {
            if (!(Character.isLetterOrDigit(c) || additionalAllowedChars.contains(c))) {
                throw new ValidationException("Password contains illegal signs");
            }
        }

        passwordSpecValidator.passwordIsValid(password);
    }
}
