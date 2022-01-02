package pl.kamilwojcik.passwordkeeper.authentication.dto.value;

import pl.kamilwojcik.passwordkeeper.validation.password.ValidAppPassword;

import javax.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String username,
        @ValidAppPassword String password
) {
}
