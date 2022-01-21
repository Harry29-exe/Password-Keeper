package pl.kamilwojcik.passwordkeeper.exceptions.general;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.kamilwojcik.passwordkeeper.exceptions.ErrorBody;
import pl.kamilwojcik.passwordkeeper.exceptions.ExceptionHandlerPrototype;
import pl.kamilwojcik.passwordkeeper.exceptions.GlobalExceptionHandler;

import static pl.kamilwojcik.passwordkeeper.exceptions.ErrorCode.BAD_REQUEST;

@Profile("dev")
@GlobalExceptionHandler
public class DevGlobalErrorHandler extends ExceptionHandlerPrototype {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT)
    public ErrorBody handleUnhandledExceptions(Exception ex) {
        logError("Unhandled exception was thrown", ex);

        return BAD_REQUEST.toErrorBody();
    }

}
