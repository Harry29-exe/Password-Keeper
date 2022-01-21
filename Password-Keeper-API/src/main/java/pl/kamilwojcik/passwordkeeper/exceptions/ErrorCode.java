package pl.kamilwojcik.passwordkeeper.exceptions;

public enum ErrorCode {

    /**
     * request
     */
    BAD_REQUEST,

    /**
     * auth
     */
    AUTHENTICATION_FAILED,
    UNKNOWN_DEVICE,
    ACCESS_DENIED,

    /**
     * resources
     */
    NO_RESOURCE;


    public ErrorBody toErrorBody() {
        return new ErrorBody(this);
    }

}
