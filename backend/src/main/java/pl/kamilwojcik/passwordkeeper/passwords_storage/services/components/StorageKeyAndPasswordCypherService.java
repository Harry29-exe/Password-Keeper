package pl.kamilwojcik.passwordkeeper.passwords_storage.services.components;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;

@Service
public class StorageKeyAndPasswordCypherService implements StorageKeyGenerator, StoragePasswordCypher {
    private final Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder();

    @Override
    public String getStorageKey(String storagePassword) {
        //todo
        return null;
    }

    @Override
    public String encryptPassword(String password, String storageKey) {
        //todo
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/ECB");
        } catch (Exception ex) {
            throw new IllegalStateException("Could not create Cypher");
        }

        return null;
    }

}
