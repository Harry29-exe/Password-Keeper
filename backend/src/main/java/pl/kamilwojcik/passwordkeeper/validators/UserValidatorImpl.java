package pl.kamilwojcik.passwordkeeper.validators;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserValidatorImpl implements UserValidator {

    private final List<Character> additionalAllowedChars = List.of('_', '@', '$');

    @Override
    public void validateUsername(String username) {
        for (var c : username.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                //todo
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void validatePassword(String password) {
        for (var c : password.toCharArray()) {
            if (!(Character.isLetterOrDigit(c) || additionalAllowedChars.contains(c))) {
                //todo
                throw new IllegalArgumentException("");
            }
        }
    }
}
