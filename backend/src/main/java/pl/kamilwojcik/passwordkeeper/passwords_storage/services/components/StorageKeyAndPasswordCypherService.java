package pl.kamilwojcik.passwordkeeper.passwords_storage.services.components;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Service
public class StorageKeyAndPasswordCypherService implements StorageKeyGenerator, StoragePasswordCypher {

    @Override
    public SecretKey getStorageKey(String storagePassword, String salt) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec keySpec = new PBEKeySpec(storagePassword.toCharArray(), salt.getBytes(StandardCharsets.UTF_8), 65536, 256);
            SecretKey key = keyFactory.generateSecret(keySpec);

            return new SecretKeySpec(key.getEncoded(), "AES");

        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("Could not get key factory");
        } catch (InvalidKeySpecException ex) {
            throw new IllegalStateException("Could not generate key");
        }
    }

    @Override
    public String encryptPassword(String password, SecretKey storageKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, storageKey);
            byte[] encryptedPassword = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(encryptedPassword);

        } catch (Exception ex) {
            throw new IllegalStateException("Could not create Cypher");
        }
    }

    @Override
    public String decodePassword(String password, SecretKey storageKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, storageKey);
            byte[] nonEncodedPass = Base64.getDecoder().decode(password);
            byte[] decodedPassword = cipher.doFinal(nonEncodedPass);

            return new String(decodedPassword);

        } catch (Exception ex) {
            throw new IllegalStateException("Could not create Cypher");
        }
    }
}
