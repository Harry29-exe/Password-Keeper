package pl.kamilwojcik.passwordkeeper.exceptions.auth.authetication;

public class JwtValidationException extends AuthenticationException {

    public JwtValidationException() {
        super("No authorization");
    }
}
