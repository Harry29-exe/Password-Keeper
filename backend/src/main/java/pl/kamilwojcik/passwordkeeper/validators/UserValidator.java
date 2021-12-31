package pl.kamilwojcik.passwordkeeper.validators;

public interface UserValidator {

    void validateUsername(String username);

    void validatePassword(String password);


}
