package pl.kamilwojcik.passwordkeeper.validation.password.specyfication;

import java.util.List;

public record PasswordSpec(
        List<Character> specialCharacters,
        Integer passwordLength,
        Integer lowerCaseChars,
        Integer upperCaseChars,
        Integer digitsChars,
        Boolean allowsNonASCII,
        Integer specialChars
) {

    public PasswordSpec {
        var minimalLength = lowerCaseChars + upperCaseChars + digitsChars + specialChars;
        if (passwordLength < minimalLength) {
            throw new IllegalStateException("Password must be at least as long as all it's necessary parts");
        }
    }
}
