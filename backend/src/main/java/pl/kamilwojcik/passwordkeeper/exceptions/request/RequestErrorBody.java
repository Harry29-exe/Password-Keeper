package pl.kamilwojcik.passwordkeeper.exceptions.request;

import pl.kamilwojcik.passwordkeeper.exceptions.ErrorBody;

public class RequestErrorBody extends ErrorBody {

    private final RequestPart missingRequestPart;
    private String requestPartName;

    public RequestErrorBody(String errorCode, RequestPart missingRequestPart) {
        super(errorCode);
        this.missingRequestPart = missingRequestPart;
    }

    public RequestErrorBody(String errorCode, RequestPart missingRequestPart, String requestPartName) {
        super(errorCode);
        this.missingRequestPart = missingRequestPart;
        this.requestPartName = requestPartName;
    }

    public enum RequestPart {
        HEADER,
        COOKIE,
        BODY
    }

}
