package pl.kamilwojcik.passwordkeeper.validation.text;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UrlLikeValidator implements ConstraintValidator<UrlLike, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
