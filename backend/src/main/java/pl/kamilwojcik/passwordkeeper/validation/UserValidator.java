package pl.kamilwojcik.passwordkeeper.validation;

public interface UserValidator {

    void validateUsername(String username);

    void validatePassword(String password);


}
