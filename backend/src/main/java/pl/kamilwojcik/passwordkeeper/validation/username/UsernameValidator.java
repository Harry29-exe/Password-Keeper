package pl.kamilwojcik.passwordkeeper.validation.username;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.charset.StandardCharsets;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

    @Override
    public void initialize(ValidUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username == null || username.isBlank()) {
            return false;
        }

        if (!StandardCharsets.US_ASCII.newEncoder().canEncode(username)) {
            return false;
        }

        for (var c : username.toCharArray()) {

            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }

        return true;
    }

}
