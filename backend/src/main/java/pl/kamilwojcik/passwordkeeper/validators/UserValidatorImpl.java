package pl.kamilwojcik.passwordkeeper.validators;

import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.List;

@Component
public class UserValidatorImpl implements UserValidator {
    private final PasswordRequirementsValidator passwordRequirementsValidator;

    private final List<Character> additionalAllowedChars = List.of('_', '@', '$');

    public UserValidatorImpl(PasswordRequirementsValidator passwordRequirementsValidator) {
        this.passwordRequirementsValidator = passwordRequirementsValidator;
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

        passwordRequirementsValidator.validatePassword(password);
    }
}
