package pl.kamilwojcik.passwordkeeper.validation.password.in_storage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordInStorageValidator implements ConstraintValidator<PasswordInStorage, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //todo
        return true;
    }
}
