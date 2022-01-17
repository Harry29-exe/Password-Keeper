package pl.kamilwojcik.passwordkeeper.validation.password.app;

import pl.kamilwojcik.passwordkeeper.validation.password.specyfication.PasswordSpecValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AppPasswordValidator implements ConstraintValidator<AppPassword, String> {
    private final PasswordSpecValidator passwordSpecValidator;

    public AppPasswordValidator(PasswordSpecValidator passwordSpecValidator) {
        this.passwordSpecValidator = passwordSpecValidator;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
//        context.buildConstraintViolationWithTemplate("Invalid password format").addConstraintViolation();
        return passwordSpecValidator.passwordIsValid(value);
    }
}
