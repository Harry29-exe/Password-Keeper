package pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record SavePasswordRequest(
        @NotBlank String storagePassword,
        @NotNull String passwordToSave,
        @NotBlank String passwordName,
        @Nullable String passwordUrl
) {
}
