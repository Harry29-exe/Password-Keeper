package pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests;

import pl.kamilwojcik.passwordkeeper.validation.password.app.AppPassword;
import pl.kamilwojcik.passwordkeeper.validation.password.in_storage.PasswordInStorage;
import pl.kamilwojcik.passwordkeeper.validation.text.SimpleText;
import pl.kamilwojcik.passwordkeeper.validation.text.UrlLike;

public record SavePasswordRequest(
        @AppPassword String storagePassword,
        @PasswordInStorage String passwordToSave,
        @SimpleText String passwordName,
        @UrlLike String passwordUrl
) {
}
