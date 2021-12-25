package pl.kamilwojcik.passwordkeeper.passwords_storage.services.components;

import javax.crypto.SecretKey;

public interface StoragePasswordCypher {

    String encryptPassword(String password, SecretKey storageKey);

    String decodePassword(String password, SecretKey storageKey);

}
