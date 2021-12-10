package pl.kamilwojcik.passwordkeeper.passwords_storage.api.dto;

import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;

public record CreateFuturePasswordsRequest(
        @NotBlank String storagePassword
) {
}
