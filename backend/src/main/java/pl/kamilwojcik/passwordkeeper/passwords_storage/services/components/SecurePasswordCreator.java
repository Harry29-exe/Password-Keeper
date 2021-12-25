package pl.kamilwojcik.passwordkeeper.passwords_storage.services.components;

import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordRequirements;

public interface SecurePasswordCreator {

    String createSecurePassword();

    String createSecurePassword(PasswordRequirements passwordRequirements);

}
