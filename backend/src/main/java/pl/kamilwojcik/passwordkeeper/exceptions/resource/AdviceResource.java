package pl.kamilwojcik.passwordkeeper.exceptions.resource;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kamilwojcik.passwordkeeper.exceptions.ErrorBody;

import java.util.logging.Logger;

@RestControllerAdvice
public class AdviceResource {

    private final String ERROR_CODE = "RESOURCE_NOT_FOUND";
    private final Logger logger = Logger.getLogger(AdviceResource.class.getName());

    @ExceptionHandler(ResourceNotExistException.class)
    public ErrorBody handleResourceNotFound(ResourceNotExistException ex) {
        return new ResourceErrorBody(ERROR_CODE, ex.getResourceType(), ex.getResourceName());
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ErrorBody generalResourceExceptionHandler(ResourceAlreadyExistException ex) {
        return new ErrorBody(ERROR_CODE);
    }

    @ExceptionHandler({IllegalNoResourceException.class})
    public ErrorBody handleIllegalNoResource(IllegalNoResourceException ex) {
        logger.warning("IllegalNoResourceException has been thrown in class: "
                + ex.getStackTrace()[0].getClassName() + ex.getStackTrace()[0].getMethodName());
        return new ErrorBody(ERROR_CODE);
    }

}
