package pl.kamilwojcik.passwordkeeper.authentication.dto.value;

import javax.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password
) {
}
