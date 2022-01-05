package pl.kamilwojcik.passwordkeeper.exceptions.auth;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kamilwojcik.passwordkeeper.exceptions.ErrorBody;

@RestControllerAdvice
public class AdviceAuthentication {

    private final String ERROR_CODE = "INVALID_CREDENTIALS";

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AuthenticationException.class})
    public ErrorBody handleAuthenticationException(AuthenticationException ex) {
        return new ErrorBody(ERROR_CODE);
    }

    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler({NotAuthorizedException.class})
    public ErrorBody handleAuthorizationException(NotAuthorizedException ex) {
        return new ErrorBody(ERROR_CODE);
    }


    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            JWTCreationException.class,
            JWTDecodeException.class,
            JWTVerificationException.class})
    public ErrorBody handleJwtException(Exception ex) {
        return new ErrorBody(ERROR_CODE);
    }


    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            DeviceNotAuthorizedException.class
    })
    public ErrorBody handleUnauthorizedDevice(DeviceNotAuthorizedException ex) {
        return new ErrorBody(ERROR_CODE);
    }


}
