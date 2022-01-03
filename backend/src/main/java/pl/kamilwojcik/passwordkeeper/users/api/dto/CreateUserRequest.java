package pl.kamilwojcik.passwordkeeper.users.api.dto;

import pl.kamilwojcik.passwordkeeper.validation.password.app.AppPassword;
import pl.kamilwojcik.passwordkeeper.validation.username.ValidUsername;

import javax.validation.constraints.Email;

public record CreateUserRequest(
        @ValidUsername String username,
        @Email String email,
        @AppPassword String password,
        @AppPassword String storagePassword
) {
}
