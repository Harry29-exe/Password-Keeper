package pl.kamilwojcik.passwordkeeper.passwords_storage.services.dto;

public record AddNewPassword(
        String nonEncodedPassword,
        String passwordName,
        String storagePassword
) {
}
