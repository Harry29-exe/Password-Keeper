package pl.kamilwojcik.passwordkeeper.validation.password.specyfication;

import org.springframework.stereotype.Component;

@Component
public class PasswordSpecValidatorImpl implements PasswordSpecValidator {
    private final PasswordSpec defaultPasswordSpec;

    public PasswordSpecValidatorImpl(PasswordSpec defaultPasswordSpec) {
        this.defaultPasswordSpec = defaultPasswordSpec;
    }

    @Override
    public boolean passwordIsValid(String password, PasswordSpec requirements) {
        if(password == null || password.isBlank()) {
            return false;
        }
        if (password.length() < requirements.passwordLength()) {
            return false;
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
            } else if (requirements.specialCharacters().contains(c)){
                special++;
            } else {
                return false;
            }
        }

        return lowercase >= requirements.lowerCaseChars() &&
                uppercase >= requirements.upperCaseChars() &&
                digits >= requirements.digitsChars() &&
                special >= requirements.specialChars();
    }

    @Override
    public boolean passwordIsValid(String password) {
        return this.passwordIsValid(password, defaultPasswordSpec);
    }

}
