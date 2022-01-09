package pl.kamilwojcik.passwordkeeper.authentication.api.dto;

import pl.kamilwojcik.passwordkeeper.validation.password.app.AppPassword;

import javax.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String username,
        @AppPassword String password
) {
}
