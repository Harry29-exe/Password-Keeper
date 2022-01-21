package pl.kamilwojcik.passwordkeeper.exceptions.auth.authetication;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super("Could not authenticate");
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
