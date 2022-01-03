package pl.kamilwojcik.passwordkeeper.validation.text_or_digit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SimpleTextValidator implements ConstraintValidator<SimpleText, String> {
    private boolean nullable;
    private boolean canBeBlank;

    @Override
    public void initialize(SimpleText constraintAnnotation) {
        nullable = constraintAnnotation.nullable();
        canBeBlank = constraintAnnotation.canBeBlank();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return nullable;
        }

        if(value.isBlank()) {
            return canBeBlank;
        }

        for(char c : value.toCharArray()) {
            if(!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        
        return true;
    }
}
