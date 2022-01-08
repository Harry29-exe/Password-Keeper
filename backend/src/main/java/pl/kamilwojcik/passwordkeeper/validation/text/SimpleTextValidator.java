package pl.kamilwojcik.passwordkeeper.validation.text;

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
        if (value == null) {
            return nullable;
        }

        if (value.isBlank()) {
            return canBeBlank;
        }

        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isWhitespace(chars[i])) {
                //no chars at the beginning of string
                if (i == 0 || i == chars.length - 1) {
                    return false;
                    //no multiple chars
                } else if (Character.isWhitespace(chars[i - 1])) {
                    return false;
                }
            } else if (!Character.isLetterOrDigit(chars[i])) {
                return false;
            }
        }

        return true;
    }
}
