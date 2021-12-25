package pl.kamilwojcik.passwordkeeper.passwords_storage.services.components;

public interface StorageKeyGenerator {

    String getStorageKey(String storagePassword);

}
