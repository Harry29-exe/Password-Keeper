package pl.kamilwojcik.passwordkeeper.exceptions.error.server;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.kamilwojcik.passwordkeeper.exceptions.ErrorBody;
import pl.kamilwojcik.passwordkeeper.exceptions.ExceptionHandlerPrototype;
import pl.kamilwojcik.passwordkeeper.exceptions.ModuleExceptionHandler;

import static pl.kamilwojcik.passwordkeeper.exceptions.ErrorCode.BAD_REQUEST;


@ModuleExceptionHandler
public class AdviceServerError extends ExceptionHandlerPrototype {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ErrorBody handleIllegalStateException(IllegalStateException ex) {
        this.logError(
                "Illegal state exception was throw with " +
                        "probably indicated some sort of bug,",
                ex);

        return BAD_REQUEST.toErrorBody();
    }


}
