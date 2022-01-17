package pl.kamilwojcik.passwordkeeper.validation.password.in_storage;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordInStorageValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordInStorage {

    String message() default "{pl.kamilwojcik.passwordkeeper.validation.password.in_storage.PasswordInStorage.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
