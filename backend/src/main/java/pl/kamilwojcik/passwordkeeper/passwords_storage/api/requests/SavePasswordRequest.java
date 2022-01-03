package pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests;

import org.springframework.lang.Nullable;
import pl.kamilwojcik.passwordkeeper.validation.password.app.AppPassword;
import pl.kamilwojcik.passwordkeeper.validation.password.in_storage.PasswordInStorage;
import pl.kamilwojcik.passwordkeeper.validation.text_or_digit.SimpleText;

public record SavePasswordRequest(
        @AppPassword String storagePassword,
        @PasswordInStorage String passwordToSave,
        @SimpleText String passwordName,
        @SimpleText(nullable = true) String passwordUrl
) {
}
