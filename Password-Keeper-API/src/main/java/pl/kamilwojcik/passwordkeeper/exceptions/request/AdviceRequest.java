package pl.kamilwojcik.passwordkeeper.exceptions.request;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.kamilwojcik.passwordkeeper.exceptions.ErrorBody;
import pl.kamilwojcik.passwordkeeper.exceptions.ExceptionHandlerPrototype;
import pl.kamilwojcik.passwordkeeper.exceptions.ModuleExceptionHandler;

import static pl.kamilwojcik.passwordkeeper.exceptions.ErrorCode.BAD_REQUEST;

@ModuleExceptionHandler
public class AdviceRequest extends ExceptionHandlerPrototype {

    private final String ERROR_CODE = "MISSING_REQUEST_ATTRIBUTE";

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NoRequiredHeaderException.class})
    public ErrorBody handleNoRequiredHeaderException(NoRequiredHeaderException ex) {
        logException(ex);

        return BAD_REQUEST.toErrorBody();
    }


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoRequiredCookieException.class)
    public ErrorBody handleNoRequiredCookie(NoRequiredCookieException ex) {
        logException(ex);

        return BAD_REQUEST.toErrorBody();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidRequestException.class})
    public ErrorBody handleGeneralInvalidRequestException(InvalidRequestException ex) {
        logException(ex);

        return BAD_REQUEST.toErrorBody();
    }

}
