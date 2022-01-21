package pl.kamilwojcik.passwordkeeper.exceptions.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.kamilwojcik.passwordkeeper.exceptions.ErrorBody;
import pl.kamilwojcik.passwordkeeper.exceptions.ExceptionHandlerPrototype;
import pl.kamilwojcik.passwordkeeper.exceptions.ModuleExceptionHandler;

import javax.validation.ValidationException;

import static pl.kamilwojcik.passwordkeeper.exceptions.ErrorCode.BAD_REQUEST;

@ModuleExceptionHandler
public class AdviceValidation extends ExceptionHandlerPrototype {
    private final String ERROR_CODE = "INPUT_VALUES_CONSTRAINT_VIOLATION";

    @ExceptionHandler({ValidationException.class})
    public ErrorBody handleJavaxValidationExceptions(ValidationException ex) {
        //todo marge to method below
        return BAD_REQUEST.toErrorBody();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorBody handleArgumentNotValidException(MethodArgumentNotValidException ex) {
//        System.out.println("\n"+ex.getMessage()+"\n");
        return BAD_REQUEST.toErrorBody();
        //todo if controller return bad request otherwise print exception stack trace
//        boolean appendMsg = false;
//
//        Method method = ex.getParameter().getMethod();
//        if (method != null) {
//            Class<?> clazz = method.getDeclaringClass();
//            appendMsg = clazz.getAnnotation(RestController.class) != null;
//        }
//
//        if (appendMsg) {
//            List<String> notValidFieldsNames = new ArrayList<>();
//            ex.getBindingResult().getAllErrors().forEach((error) -> {
//                FieldError fieldError = (FieldError) error;
//                String fieldName = fieldError.getField();
//                notValidFieldsNames.add(fieldName);
//            });
//
//            return new ValidationErrorBody(ERROR_CODE, notValidFieldsNames);
//        }
//
//        return new ErrorBody(ERROR_CODE);
    }


}
