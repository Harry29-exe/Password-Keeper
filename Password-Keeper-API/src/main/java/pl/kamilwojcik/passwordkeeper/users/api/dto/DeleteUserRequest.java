package pl.kamilwojcik.passwordkeeper.users.api.dto;

import pl.kamilwojcik.passwordkeeper.validation.password.app.AppPassword;
import pl.kamilwojcik.passwordkeeper.validation.username.ValidUsername;

public record DeleteUserRequest(
        @ValidUsername String username,
        @AppPassword String password
) {
}
