package pl.kamilwojcik.passwordkeeper.validators;

import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordRequirements;

public interface PasswordRequirementsValidator {

    /**
     * Checks if passwords fulfills all criteria given in passwordRequirements param
     * @param password password to be validated
     * @param passwordRequirements password requirements for password
     */
    void validatePassword(String password, PasswordRequirements passwordRequirements);

    /**
     * Checks if passwords fulfills all criteria given in PasswordRequirements Bean
     * @param password password to be validated
     */
    void validatePassword(String password);

}
