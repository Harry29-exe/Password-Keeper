package pl.kamilwojcik.passwordkeeper.exceptions.resource;

import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.kamilwojcik.passwordkeeper.exceptions.ErrorBody;
import pl.kamilwojcik.passwordkeeper.exceptions.ExceptionHandlerPrototype;
import pl.kamilwojcik.passwordkeeper.exceptions.ModuleExceptionHandler;

import static java.lang.String.format;
import static pl.kamilwojcik.passwordkeeper.exceptions.ErrorCode.BAD_REQUEST;
import static pl.kamilwojcik.passwordkeeper.exceptions.ErrorCode.NO_RESOURCE;

@ModuleExceptionHandler
public class AdviceResource extends ExceptionHandlerPrototype {

    @ExceptionHandler(ResourceNotExistException.class)
    public ErrorBody handleResourceNotFound(ResourceNotExistException ex) {
        return handleException(ex, NO_RESOURCE);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ErrorBody generalResourceExceptionHandler(ResourceAlreadyExistException ex) {
        return handleException(ex, NO_RESOURCE);
    }

    @ExceptionHandler({IllegalNoResourceException.class})
    public ErrorBody handleIllegalNoResource(IllegalNoResourceException ex) {
        logError(
                format("IllegalNoResourceException has been thrown in class: %s in method: %s",
                        ex.getStackTrace()[0].getClassName(),
                        ex.getStackTrace()[0].getMethodName()),
                ex
        );

        return BAD_REQUEST.toErrorBody();
    }

}
