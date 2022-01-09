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

    private final String CREDENTIALS_ERROR_CODE = "INVALID_CREDENTIALS";
    private final String UNKNOWN_DEVICE = "UNKNOWN_DEVICE";


    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AuthenticationException.class})
    public ErrorBody handleAuthenticationException(AuthenticationException ex) {
        return new ErrorBody(CREDENTIALS_ERROR_CODE);
    }


    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler({NotAuthorizedException.class})
    public ErrorBody handleAuthorizationException(NotAuthorizedException ex) {
        return new ErrorBody(CREDENTIALS_ERROR_CODE);
    }


    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            JWTCreationException.class,
            JWTDecodeException.class,
            JWTVerificationException.class})
    public ErrorBody handleJwtException(Exception ex) {
        return new ErrorBody(CREDENTIALS_ERROR_CODE);
    }


    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            DeviceNotAuthorizedException.class
    })
    public ErrorBody handleUnauthorizedDevice(DeviceNotAuthorizedException ex) {
        return new ErrorBody(CREDENTIALS_ERROR_CODE);
    }


    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnknownDeviceException.class})
    public ErrorBody handleUnknownDeviceException(UnknownDeviceException ex) {
        return new ErrorBody(UNKNOWN_DEVICE);
    }

}

