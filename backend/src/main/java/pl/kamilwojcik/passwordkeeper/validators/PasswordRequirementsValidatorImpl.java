package pl.kamilwojcik.passwordkeeper.validators;

import org.springframework.stereotype.Component;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordRequirements;

import javax.validation.ValidationException;

@Component
public class PasswordRequirementsValidatorImpl implements PasswordRequirementsValidator {
    private final PasswordRequirements defaultPasswordRequirements;

    public PasswordRequirementsValidatorImpl(PasswordRequirements defaultPasswordRequirements) {
        this.defaultPasswordRequirements = defaultPasswordRequirements;
    }

    @Override
    public void validatePassword(String password, PasswordRequirements requirements) {
        if (password.length() < requirements.passwordLength()) {
            throw new ValidationException("Password length is: " + password.length() +
                    " when minimal password length is: " + requirements.passwordLength());
        }
        int lowercase = 0;
        int uppercase = 0;
        int digits = 0;
        int special = 0;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                digits++;
            } else if (Character.isLowerCase(c)) {
                lowercase++;
            } else if (Character.isUpperCase(c)) {
                uppercase++;
            } else {
                special++;
            }
        }

        var valid = lowercase >= requirements.lowerCaseChars() &&
                uppercase >= requirements.upperCaseChars() &&
                digits >= requirements.digitsChars() &&
                special >= requirements.specialChars();

        if(!valid) {
            throw new ValidationException("Password does not meet given requirements");
        }
    }

    @Override
    public void validatePassword(String password) {
        this.validatePassword(password, defaultPasswordRequirements);
    }

}
