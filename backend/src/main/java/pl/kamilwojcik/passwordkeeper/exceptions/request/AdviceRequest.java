package pl.kamilwojcik.passwordkeeper.exceptions.request;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kamilwojcik.passwordkeeper.exceptions.ErrorBody;

@RestControllerAdvice
public class AdviceRequest {

    private final String ERROR_CODE = "Missing request attribute";

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NoRequiredHeaderException.class})
    public ErrorBody handleNoRequiredHeaderException(NoRequiredHeaderException ex) {
        return new ErrorBody(ERROR_CODE);
    }

}
