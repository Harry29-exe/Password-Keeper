package pl.kamilwojcik.passwordkeeper.validation.text;

import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

@Documented
@Pattern(regexp = "[A-Z,a-z0-9-._~:/?#\\[\\]@!$&'()*+\\;%]+")
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UrlLike {
}
