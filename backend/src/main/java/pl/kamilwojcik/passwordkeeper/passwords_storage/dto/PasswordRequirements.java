package pl.kamilwojcik.passwordkeeper.passwords_storage.dto;

public record PasswordRequirements(
        Integer passwordLength,
        Integer lowerCaseChars,
        Integer upperCaseChars,
        Integer digitsChars,
        Integer specialChars
) {

    public PasswordRequirements {
        var minimalLength = lowerCaseChars + upperCaseChars + digitsChars + specialChars;
        if(passwordLength < minimalLength) {
            throw new IllegalStateException("Password must be at least as long as all it's necessary parts");
        }
    }
}
