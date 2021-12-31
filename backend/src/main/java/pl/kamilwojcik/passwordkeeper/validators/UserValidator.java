package pl.kamilwojcik.passwordkeeper.validators;

import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordRequirements;

public interface UserValidator {

    void validateUsername(String username);

    void validatePassword(String password);


}
