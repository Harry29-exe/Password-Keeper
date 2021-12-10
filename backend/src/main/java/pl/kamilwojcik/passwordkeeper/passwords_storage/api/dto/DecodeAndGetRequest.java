package pl.kamilwojcik.passwordkeeper.passwords_storage.api.dto;

import javax.validation.constraints.NotBlank;

public record DecodeAndGetRequest(
        @NotBlank String storagePassword
) {
}
