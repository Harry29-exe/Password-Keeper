package pl.kamilwojcik.passwordkeeper.validators.url;

import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlOrNullValidator implements ConstraintValidator<UrlOrNull, String> {
    private URLValidator urlValidator;

    public UrlOrNullValidator() {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        }

        URL url;
        try {
            url = new java.net.URL( value.toString() );
        }
        catch (MalformedURLException e) {
            return false;
        }

        return true;
    }

    @Override
    public void initialize(UrlOrNull constraintAnnotation) {
    }
}
