package pl.kamilwojcik.passwordkeeper.exceptions;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Profile("!dev")
@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUnhandledExceptions(Exception ex) {
        return "";
    }

}
