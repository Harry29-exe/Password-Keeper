package pl.kamilwojcik.passwordkeeper.exceptions.validation;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

@RestControllerAdvice
public class AdviceValidation {

    @ExceptionHandler({ValidationException.class})
    public String handleJavaxValidationExceptions(ValidationException ex) {
        return "";
    }


}
