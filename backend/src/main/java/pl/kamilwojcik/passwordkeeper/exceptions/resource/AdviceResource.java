package pl.kamilwojcik.passwordkeeper.exceptions.resource;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceResource {

    private final String ERROR_CODE = "RESOURCE_NOT_FOUND";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResourceErrorBody handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResourceErrorBody(ERROR_CODE, ex.getResourceType(), ex.getResourceName());
    }

}
