package pl.kamilwojcik.passwordkeeper.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceUserException {

    @ExceptionHandler(NoSuchUserException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleNoSuchUser(NoSuchUserException ex) {
        return ex.getMessage();
    }

}
