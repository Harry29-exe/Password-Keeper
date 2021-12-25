package pl.kamilwojcik.passwordkeeper.passwords_storage.services.components;

public interface StoragePasswordCypher {

    String encryptPassword(String password, String storageKey);

}
