package pl.kamilwojcik.passwordkeeper.exceptions.auth.authorization;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.kamilwojcik.passwordkeeper.exceptions.ErrorBody;
import pl.kamilwojcik.passwordkeeper.exceptions.ExceptionHandlerPrototype;
import pl.kamilwojcik.passwordkeeper.exceptions.ModuleExceptionHandler;

import static pl.kamilwojcik.passwordkeeper.exceptions.ErrorCode.ACCESS_DENIED;

@ModuleExceptionHandler
public class AdviceAuthorization extends ExceptionHandlerPrototype {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    public ErrorBody handleAccessDeniedException(AccessDeniedException ex) {
        logException(ex);
        return ACCESS_DENIED.toErrorBody();
    }



}
