package pl.kamilwojcik.passwordkeeper.exceptions;

import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static pl.kamilwojcik.passwordkeeper.exceptions.ErrorHandlerPriority.GLOBAL_HANDLER;

@Profile("dev")
@Order(GLOBAL_HANDLER)
@RestControllerAdvice
public class DevGlobalErrorHandler {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT)
    public String handleUnhandledExceptions(Exception ex) {
        ex.printStackTrace();
        return "";
    }

}
