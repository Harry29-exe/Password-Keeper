package pl.kamilwojcik.passwordkeeper.users.api.dto;

import javax.validation.constraints.NotBlank;

public record CreateUserRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String storagePassword
) {
}
