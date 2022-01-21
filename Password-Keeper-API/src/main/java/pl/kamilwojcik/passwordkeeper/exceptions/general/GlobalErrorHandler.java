package pl.kamilwojcik.passwordkeeper.exceptions.general;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.kamilwojcik.passwordkeeper.exceptions.ErrorBody;
import pl.kamilwojcik.passwordkeeper.exceptions.ExceptionHandlerPrototype;
import pl.kamilwojcik.passwordkeeper.exceptions.GlobalExceptionHandler;

import static pl.kamilwojcik.passwordkeeper.exceptions.ErrorCode.BAD_REQUEST;

@GlobalExceptionHandler
public class GlobalErrorHandler extends ExceptionHandlerPrototype {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorBody handleUnhandledExceptions(Exception ex) {
        this.logError(
                "Illegal state exception was throw with " +
                        "probably indicated some sort of bug,",
                ex);

        return BAD_REQUEST.toErrorBody();
    }

}
