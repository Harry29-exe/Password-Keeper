package pl.kamilwojcik.passwordkeeper.exceptions.error.server;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kamilwojcik.passwordkeeper.exceptions.ErrorBody;

import java.util.logging.Logger;

@Profile("dev")
@RestControllerAdvice
public class DevAdviceServerError {

    private final Logger logger = Logger.getLogger(DevAdviceServerError.class.getName());
    private final String ERROR_CODE = "INVALID_REQUEST";

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public ErrorBody handleIllegalStateException(IllegalStateException ex) {
        logger.warning("Not expected exception has been thrown");
        ex.printStackTrace();

        return new ErrorBody(ERROR_CODE);
    }

}
