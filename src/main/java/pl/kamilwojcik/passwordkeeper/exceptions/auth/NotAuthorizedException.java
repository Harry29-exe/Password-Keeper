package pl.kamilwojcik.passwordkeeper.exceptions.auth;

public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException() {
        super("No authorization");
    }
}
