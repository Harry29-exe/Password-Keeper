package pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

public record CreateNewSecurePassword(
        @NotBlank String storagePassword,
        @NotBlank String passwordName,
        @Nullable String passwordUrl
) {}
