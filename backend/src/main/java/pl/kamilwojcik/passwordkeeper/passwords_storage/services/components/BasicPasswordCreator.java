package pl.kamilwojcik.passwordkeeper.passwords_storage.services.components;

import org.springframework.stereotype.Component;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordRequirements;

import java.nio.ByteBuffer;
import java.security.SecureRandom;

@Component
public class BasicPasswordCreator implements SecurePasswordCreator {
    //todo sprawdzić to
    private final SecureRandom secureRandom = new SecureRandom();
    private final char[] lowerCaseChars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private final char[] upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final char[] digitsChars = "1234567890".toCharArray();
    private final char[] specialChars = "!@#$%&*(){}[]?/_".toCharArray();
    //todo should not it be like 64 so all chars have equal probability?
    private final char[] allowedChars = (String.valueOf(lowerCaseChars) +
            String.valueOf(upperCaseChars) +
            String.valueOf(digitsChars) +
            String.valueOf(specialChars))
            .toCharArray();

    private final PasswordRequirements defaultPasswordRequirements = new PasswordRequirements(
            24, 6, 2, 2, 1
    );

    @Override
    public String createSecurePassword() {
        return this.createSecurePassword(defaultPasswordRequirements);
    }

    //todo its not really well optimized (creating password in loop)
    @Override
    public String createSecurePassword(PasswordRequirements passwordRequirements) {
        String password = null;
        while (password == null) {
            String passwordCandidate = tryGenerateSecurePassword(passwordRequirements.passwordLength());
            if(validatePassword(passwordCandidate, passwordRequirements)) {
                password = passwordCandidate;
            }
        }

        return password;
    }
    
    private boolean validatePassword(String password, PasswordRequirements criteria) {
        if(password.length() < criteria.passwordLength()) {
            return false;
        }
        int lowercase = 0;
        int uppercase = 0;
        int digits = 0;
        int special = 0;

        for(char c : password.toCharArray()) {
            if(Character.isDigit(c)) {
                digits++;
            } else if(Character.isLowerCase(c)) {
                lowercase++;
            } else if(Character.isUpperCase(c)) {
                uppercase++;
            } else {
                special++;
            }
        }

        return lowercase >= criteria.lowerCaseChars() &&
                uppercase >= criteria.upperCaseChars() &&
                digits >= criteria.digitsChars() &&
                special >= criteria.specialChars();
    }



    private String tryGenerateSecurePassword(Integer passwordLength) {
        StringBuilder passwordBuilder = new StringBuilder();

        byte[] bytes = new byte[passwordLength * 4];
        secureRandom.nextBytes(bytes);
        ByteBuffer randomBytes = ByteBuffer.wrap(bytes);

        for(int i = 0; i < passwordLength; i++) {
            int charCode = randomBytes.getInt() % allowedChars.length;
            passwordBuilder.append(allowedChars[charCode]);
        }

        return passwordBuilder.toString();
    }



}
