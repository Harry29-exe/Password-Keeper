package pl.kamilwojcik.passwordkeeper.exceptions.validation;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kamilwojcik.passwordkeeper.exceptions.ErrorBody;

import javax.validation.ValidationException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class AdviceValidation {
    private final String ERROR_CODE = "INPUT_VALUES_CONSTRAINT_VIOLATION";

    @ExceptionHandler({ValidationException.class})
    public String handleJavaxValidationExceptions(ValidationException ex) {
        System.out.println(ex.getMessage());
        return "";
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorBody handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        boolean appendMsg = false;

        Method method = ex.getParameter().getMethod();
        if(method != null) {
            Class<?> clazz = method.getDeclaringClass();
             appendMsg = clazz.getAnnotation(RestController.class) != null;
        }

        if(appendMsg) {
            List<String> notValidFieldsNames = new ArrayList<>();
            ex.getBindingResult().getAllErrors().forEach((error) -> {
                FieldError fieldError = (FieldError) error;
                String fieldName = fieldError.getField();
                notValidFieldsNames.add(fieldName);
            });

            return new ValidationErrorBody(ERROR_CODE, notValidFieldsNames);
        }

        return new ErrorBody(ERROR_CODE);
    }


}
