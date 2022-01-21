package pl.kamilwojcik.passwordkeeper.exceptions;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.annotation.*;

@Order(Integer.MAX_VALUE)
@RestControllerAdvice
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SingleExceptionHandler {
}
