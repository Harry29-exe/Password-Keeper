package pl.kamilwojcik.passwordkeeper.passwords_storage.services.components;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Service
public class PasswordStorageDefaultCypher implements PasswordStorageCypher {
    private static final Integer PASSWORD_SIZE = 256;
    private static final Integer IV_SIZE = 128;
    private final SecureRandom secureRandom = SecureRandom.getInstanceStrong();

    public PasswordStorageDefaultCypher() throws NoSuchAlgorithmException {
    }

    @Override
    public String encryptPassword(String password, SecretKey storageKey, byte[] iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, storageKey, ivParam);
            byte[] encryptedPassword = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(encryptedPassword);

        } catch (Exception ex) {
            throw new IllegalStateException("Could not create Cypher");
        }
    }

    @Override
    public String decodePassword(String password, SecretKey storageKey, byte[] iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, storageKey, ivParam);
            byte[] nonEncodedPass = Base64.getDecoder().decode(password);
            byte[] decodedPassword = cipher.doFinal(nonEncodedPass);

            return new String(decodedPassword);

        } catch (Exception ex) {
            throw new IllegalStateException("Could not create Cypher");
        }
    }

    @Override
    public byte[] generateInputVector() {
        byte[] randomBytes = new byte[IV_SIZE / 8];
        secureRandom.nextBytes(randomBytes);
        return randomBytes;
    }

    @Override
    public byte[] generateSalt() {
        byte[] randomBytes = new byte[PASSWORD_SIZE / 8];
        secureRandom.nextBytes(randomBytes);
        return randomBytes;
    }

    @Override
    public SecretKey generateStorageKey(String storagePassword, byte[] salt) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            KeySpec keySpec = new PBEKeySpec(
                    storagePassword.toCharArray(),
                    salt,
                    65536,
                    PASSWORD_SIZE);
            SecretKey key = keyFactory.generateSecret(keySpec);

            return new SecretKeySpec(key.getEncoded(), "AES");

        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("Could not get key factory");
        } catch (InvalidKeySpecException ex) {
            throw new IllegalStateException("Could not generate key");
        }
    }

}
