package pl.kamilwojcik.passwordkeeper.exceptions.auth;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceAuthentication {

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AuthenticationException.class})
    public String handleAuthenticationException(AuthenticationException ex) {
        return "";
    }

    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    @ExceptionHandler({NotAuthorizedException.class})
    public String handleAuthorizationException(NotAuthorizedException ex) {
        return "";
    }

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            JWTCreationException.class,
            JWTDecodeException.class,
            JWTVerificationException.class})
    public String handleJwtException(Exception ex) {
        return "";
    }


}
