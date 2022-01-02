package pl.kamilwojcik.passwordkeeper.validation.password.specyfication;

public interface PasswordSpecValidator {

    /**
     * Checks if passwords fulfills all criteria given in passwordRequirements param
     * @param password password to be validated
     * @param passwordSpec password requirements for password
     */
    boolean passwordIsValid(String password, PasswordSpec passwordSpec);

    /**
     * Checks if passwords fulfills all criteria given in PasswordRequirements Bean
     * @param password password to be validated
     */
    boolean passwordIsValid(String password);

}
