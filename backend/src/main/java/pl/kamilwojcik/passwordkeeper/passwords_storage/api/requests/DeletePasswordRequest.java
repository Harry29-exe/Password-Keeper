package pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests;

import pl.kamilwojcik.passwordkeeper.validation.text.SimpleText;

public record DeletePasswordRequest(
        @SimpleText String passwordName
) {
}
