package pl.kamilwojcik.passwordkeeper.exceptions.request;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kamilwojcik.passwordkeeper.exceptions.ErrorBody;

import static pl.kamilwojcik.passwordkeeper.exceptions.ErrorHandlerPriority.MODULE_HANDLER;

@Order(MODULE_HANDLER)
@RestControllerAdvice
public class AdviceRequest {

    private final String ERROR_CODE = "MISSING_REQUEST_ATTRIBUTE";

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NoRequiredHeaderException.class})
    public ErrorBody handleNoRequiredHeaderException(NoRequiredHeaderException ex) {
        return new RequestErrorBody(
                ERROR_CODE,
                RequestErrorBody.RequestPart.HEADER,
                ex.getHeaderName()
        );
    }


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoRequiredCookieException.class)
    public ErrorBody handleNoRequiredCookie(NoRequiredCookieException ex) {
        return new RequestErrorBody(
                ERROR_CODE,
                RequestErrorBody.RequestPart.COOKIE,
                ex.getCookieName()
        );
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidRequestException.class})
    public ErrorBody handleGeneralInvalidRequestException(InvalidRequestException ex) {
        return new ErrorBody(ERROR_CODE);
    }

}
