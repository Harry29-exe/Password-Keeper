package pl.kamilwojcik.passwordkeeper.passwords_storage.services.components;

import javax.crypto.SecretKey;

public interface PasswordStorageCypher {

    String encryptPassword(String password, SecretKey storageKey, byte[] iv);

    String decodePassword(String password, SecretKey storageKey, byte[] iv);

    byte[] generateInputVector();

    byte[] generateSalt();

    SecretKey generateStorageKey(String storagePassword, byte[] salt);

}
