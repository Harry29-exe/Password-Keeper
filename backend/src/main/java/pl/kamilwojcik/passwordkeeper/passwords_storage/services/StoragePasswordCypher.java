package pl.kamilwojcik.passwordkeeper.passwords_storage.services;

import javax.crypto.SecretKey;

public interface StoragePasswordCypher {

    String encryptPassword(String password, SecretKey storageKey, byte[] iv);

    String decodePassword(String password, SecretKey storageKey, byte[] iv);

    byte[] generateInputVector();

    byte[] generateSalt();

    SecretKey generateStorageKey(String storagePassword, byte[] salt);

}
