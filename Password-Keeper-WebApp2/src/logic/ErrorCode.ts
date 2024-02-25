export enum ErrorCode {

    /**
     * request
     */
    BAD_REQUEST = "BAD_REQUEST",

    /**
     * auth
     */
    AUTHENTICATION_FAILED = "AUTHENTICATION_FAILED",
    BAD_CREDENTIALS = "BAD_CREDENTIALS",
    UNKNOWN_DEVICE = "UNKNOWN_DEVICE",
    ACCESS_DENIED = "ACCESS_DENIED",

    /**
     * resources
     */
    NO_RESOURCE = "NO_RESOURCE",


    UNDEFINED = "UNDEFINED"
}