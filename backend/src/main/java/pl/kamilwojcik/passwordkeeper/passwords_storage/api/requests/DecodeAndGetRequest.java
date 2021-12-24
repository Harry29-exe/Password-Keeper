package pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public record DecodeAndGetRequest(
        @NotBlank String storagePassword,
        @NotNull UUID passwordPubId
) {
}
