package pl.kamilwojcik.passwordkeeper.validators.password;

import pl.kamilwojcik.passwordkeeper.validators.password.specyfication.PasswordSpecValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AppPasswordValidator implements ConstraintValidator<ValidAppPassword, String> {
    private final PasswordSpecValidator passwordSpecValidator;

    public AppPasswordValidator(PasswordSpecValidator passwordSpecValidator) {
        this.passwordSpecValidator = passwordSpecValidator;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return passwordSpecValidator.passwordIsValid(value);
    }

    @Override
    public void initialize(ValidAppPassword constraintAnnotation) {

    }
}
