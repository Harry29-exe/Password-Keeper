package pl.kamilwojcik.passwordkeeper.passwords_storage.services.components;

import org.springframework.stereotype.Component;
import pl.kamilwojcik.passwordkeeper.validation.password.specyfication.PasswordSpec;
import pl.kamilwojcik.passwordkeeper.validation.password.specyfication.PasswordSpecValidator;

import javax.validation.ValidationException;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class SecurePasswordDefaultCreator implements SecurePasswordCreator {
    private final PasswordSpecValidator passwordValidator;
    private final PasswordSpec defaultPasswordSpec;
    //TODO-ASK sprawdzić to
    private final SecureRandom secureRandom = SecureRandom.getInstanceStrong();
    private final char[] lowerCaseChars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final char[] upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final char[] digitsChars = "1234567890".toCharArray();
    private final char[] specialChars = "!@#$%&*(){}[]?/_".toCharArray();
    //TODO-ASK should not it be like 64 so all chars have equal probability?
    private final char[] allowedChars = (String.valueOf(lowerCaseChars) +
            String.valueOf(upperCaseChars) +
            String.valueOf(digitsChars) +
            String.valueOf(specialChars))
            .toCharArray();

    public SecurePasswordDefaultCreator(PasswordSpecValidator passwordValidator, PasswordSpec defaultPasswordSpec) throws NoSuchAlgorithmException {
        this.passwordValidator = passwordValidator;
        this.defaultPasswordSpec = defaultPasswordSpec;
    }

    @Override
    public String createSecurePassword() {
        return this.createSecurePassword(defaultPasswordSpec);
    }

    //TODO-ASK its not really well optimized (creating password in loop)
    @Override
    public String createSecurePassword(PasswordSpec passwordSpec) {
        String password = null;
        while (password == null) {
            String passwordCandidate = tryGenerateSecurePassword(passwordSpec.passwordLength());
            if (validatePassword(passwordCandidate, passwordSpec)) {
                password = passwordCandidate;
            }
        }

        return password;
    }

    private boolean validatePassword(String password, PasswordSpec criteria) {
        try {
            passwordValidator.passwordIsValid(password, criteria);
        } catch (ValidationException exception) {
            return false;
        }

        return true;
    }


    private String tryGenerateSecurePassword(Integer passwordLength) {
        StringBuilder passwordBuilder = new StringBuilder();

        byte[] bytes = new byte[passwordLength * 4];
        secureRandom.nextBytes(bytes);
        ByteBuffer randomBytes = ByteBuffer.wrap(bytes);

        for (int i = 0; i < passwordLength; i++) {
            int charCode = Math.abs(randomBytes.getInt()) % allowedChars.length;
            passwordBuilder.append(allowedChars[charCode]);
        }

        return passwordBuilder.toString();
    }


}
