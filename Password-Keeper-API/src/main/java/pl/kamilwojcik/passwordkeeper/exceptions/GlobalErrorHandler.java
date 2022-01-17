package pl.kamilwojcik.passwordkeeper.exceptions;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Logger;

import static pl.kamilwojcik.passwordkeeper.exceptions.ErrorHandlerPriority.GLOBAL_HANDLER;

//@Profile("!dev")
@Order(GLOBAL_HANDLER)
@RestControllerAdvice
public class GlobalErrorHandler {
    private final Logger logger = Logger.getLogger(GlobalErrorHandler.class.getName());

    @ExceptionHandler({Exception.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorBody handleUnhandledExceptions(Exception ex) {
        logger.warning("Unsupported exception " + ex.getClass() + " has been thrown with message: " + ex.getMessage());
        System.out.println("Unsupported exception " + ex.getClass() + " has been thrown with message: " + ex.getMessage());
        return new ErrorBody("BAD_REQUEST");
    }

}
