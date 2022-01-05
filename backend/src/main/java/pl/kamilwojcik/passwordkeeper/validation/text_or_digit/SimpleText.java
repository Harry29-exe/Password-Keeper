package pl.kamilwojcik.passwordkeeper.validation.text_or_digit;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SimpleTextValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SimpleText {

    boolean nullable() default false;

    boolean canBeBlank() default false;

    String message() default "{pl.kamilwojcik.passwordkeeper.validation.text_or_digit.SimpleText.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
