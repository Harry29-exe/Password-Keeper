package pl.kamilwojcik.passwordkeeper.config;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

@CrossOrigin
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultCors {

    @AliasFor(annotation = CrossOrigin.class)
    String[] origins() default {"https://localhost:4430", "https://192.168.0.185:4430", "http://localhost:3000"};

    @AliasFor(annotation = CrossOrigin.class)
    String[] allowedHeaders() default {"Authorization", "Content-Type"};

    @AliasFor(annotation = CrossOrigin.class)
    String[] exposedHeaders() default {};

    @AliasFor(annotation = CrossOrigin.class)
    RequestMethod[] methods() default {};

    @AliasFor(annotation = CrossOrigin.class)
    String allowCredentials() default "false";

    @AliasFor(annotation = CrossOrigin.class)
    long maxAge() default -1;

}
