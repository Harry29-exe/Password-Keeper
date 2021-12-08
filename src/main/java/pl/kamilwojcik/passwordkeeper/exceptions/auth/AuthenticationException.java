package pl.kamilwojcik.passwordkeeper.exceptions.auth;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super("Could not authenticate");
    }
}
