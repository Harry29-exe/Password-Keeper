package pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests;

import javax.validation.constraints.NotBlank;

public record DeletePasswordRequest(
        @NotBlank String passwordName
) {
}
