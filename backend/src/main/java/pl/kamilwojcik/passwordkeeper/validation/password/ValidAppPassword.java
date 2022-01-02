package pl.kamilwojcik.passwordkeeper.validation.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AppPasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAppPassword {

    String message() default "{pl.kamilwojcik.passwordkeeper.validators.password.ValidAppPassword.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
