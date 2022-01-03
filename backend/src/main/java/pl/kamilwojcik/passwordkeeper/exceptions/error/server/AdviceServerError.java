package pl.kamilwojcik.passwordkeeper.exceptions.error.server;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kamilwojcik.passwordkeeper.exceptions.ErrorBody;

import java.util.logging.Logger;

@RestControllerAdvice
public class AdviceServerError {
    private final Logger logger = Logger.getLogger(AdviceServerError.class.getName());
    private final String ERROR_CODE = "INVALID_REQUEST";

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ErrorBody handleIllegalStateException(IllegalStateException ex) {
        logger.warning("Exception was throw with probably indicated some sort of bug," +
                " " + ex.getMessage() + " " + ex.getCause());

        return new ErrorBody(ERROR_CODE);
    }


}
