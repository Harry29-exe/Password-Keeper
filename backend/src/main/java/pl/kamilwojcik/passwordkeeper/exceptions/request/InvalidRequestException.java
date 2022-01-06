package pl.kamilwojcik.passwordkeeper.exceptions.request;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException() {
    }

    public InvalidRequestException(String message) {
        super(message);
    }
}
