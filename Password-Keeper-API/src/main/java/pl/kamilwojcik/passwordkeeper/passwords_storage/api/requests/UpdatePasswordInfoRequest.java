package pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

public record UpdatePasswordInfoRequest(
        @NotBlank String passwordName,
        @Nullable String newPasswordName,
        @Nullable String passwordUrl
) {
}
