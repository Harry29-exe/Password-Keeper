package pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests;

import pl.kamilwojcik.passwordkeeper.validation.password.app.AppPassword;
import pl.kamilwojcik.passwordkeeper.validation.text.SimpleText;

public record DecodeAndGetRequest(
        @AppPassword String storagePassword,
        @SimpleText String passwordName
) {
}
