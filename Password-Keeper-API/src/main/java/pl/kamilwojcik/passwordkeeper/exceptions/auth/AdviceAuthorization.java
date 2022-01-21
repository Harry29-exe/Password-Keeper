package pl.kamilwojcik.passwordkeeper.exceptions.auth;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kamilwojcik.passwordkeeper.exceptions.ErrorBody;

import static pl.kamilwojcik.passwordkeeper.exceptions.ErrorHandlerPriority.MODULE_HANDLER;

@Order(MODULE_HANDLER)
@RestControllerAdvice
public class AdviceAuthorization {

    private final String AUTHORIZATION_EXCEPTION = "AUTHORIZATION_EXCEPTION";

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    public ErrorBody handleAccessDeniedException(AccessDeniedException ex) {
        return new ErrorBody(AUTHORIZATION_EXCEPTION);
    }


}
