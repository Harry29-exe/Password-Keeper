package pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests;

import org.springframework.lang.Nullable;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordRequirements;

import javax.validation.constraints.NotBlank;

public record CreateNewPasswordRequest(
        @NotBlank String storagePassword,
        @NotBlank String passwordName,
        @Nullable String passwordUrl,
        @Nullable PasswordRequirements passwordRequirements
) {}
