package pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests;

import pl.kamilwojcik.passwordkeeper.validation.text_or_digit.SimpleText;

public record DeletePasswordRequest(
        @SimpleText String passwordName
) {
}
