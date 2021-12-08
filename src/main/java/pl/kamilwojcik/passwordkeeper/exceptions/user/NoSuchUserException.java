package pl.kamilwojcik.passwordkeeper.exceptions.user;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException() {
        super("User does not exist");
    }
}
