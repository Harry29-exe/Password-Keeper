package pl.kamilwojcik.passwordkeeper.passwords_storage.dto.values;

public record AddNewPassword(
        String nonEncodedPassword,
        String passwordName,
        String storagePassword
) {
}
