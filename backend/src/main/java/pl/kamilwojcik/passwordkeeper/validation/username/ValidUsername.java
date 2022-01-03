package pl.kamilwojcik.passwordkeeper.validation.username;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUsername {

    String message() default "{pl.kamilwojcik.passwordkeeper.validation.username.ValidUsername.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
