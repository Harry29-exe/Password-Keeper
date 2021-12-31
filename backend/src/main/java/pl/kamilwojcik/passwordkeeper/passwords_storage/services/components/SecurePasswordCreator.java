package pl.kamilwojcik.passwordkeeper.passwords_storage.services.components;

import pl.kamilwojcik.passwordkeeper.validators.password.specyfication.PasswordSpec;

public interface SecurePasswordCreator {

    String createSecurePassword();

    String createSecurePassword(PasswordSpec passwordSpec);

}
