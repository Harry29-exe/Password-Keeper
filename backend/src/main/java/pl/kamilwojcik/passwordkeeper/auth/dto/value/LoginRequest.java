package pl.kamilwojcik.passwordkeeper.auth.dto.value;

import javax.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password
) {
}
