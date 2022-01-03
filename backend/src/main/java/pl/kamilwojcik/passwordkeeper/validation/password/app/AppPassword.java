package pl.kamilwojcik.passwordkeeper.validation.password.app;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AppPasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AppPassword {

    String message() default "{pl.kamilwojcik.passwordkeeper.validators.password.app.ValidAppPassword.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
