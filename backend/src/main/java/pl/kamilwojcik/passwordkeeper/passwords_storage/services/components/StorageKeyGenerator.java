package pl.kamilwojcik.passwordkeeper.passwords_storage.services.components;

import javax.crypto.SecretKey;

public interface StorageKeyGenerator {

    SecretKey getStorageKey(String storagePassword, String salt);

}
