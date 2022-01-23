package pl.kamilwojcik.passwordkeeper.validation.password.in_storage;

import pl.kamilwojcik.passwordkeeper.validation.password.specyfication.PasswordSpec;
import pl.kamilwojcik.passwordkeeper.validation.password.specyfication.PasswordSpecValidator;
import pl.kamilwojcik.passwordkeeper.validation.password.specyfication.PasswordSpecValidatorImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static pl.kamilwojcik.passwordkeeper.config.security.PasswordRequirementsConfig.defaultSpecialChars;

public class PasswordInStorageValidator implements ConstraintValidator<PasswordInStorage, String> {

    private final PasswordSpecValidator passwordSpecValidator = new PasswordSpecValidatorImpl(new PasswordSpec(
            defaultSpecialChars, 8, 1, 1, 1, true, 1
    ));

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return passwordSpecValidator.passwordIsValid(value);
    }
}
