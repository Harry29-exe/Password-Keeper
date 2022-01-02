package pl.kamilwojcik.passwordkeeper.users.api.dto;

import pl.kamilwojcik.passwordkeeper.validation.password.ValidAppPassword;
import pl.kamilwojcik.passwordkeeper.validation.username.ValidUsername;

public record DeleteUserRequest(
        @ValidUsername String username,
        @ValidAppPassword String password
) {
}
