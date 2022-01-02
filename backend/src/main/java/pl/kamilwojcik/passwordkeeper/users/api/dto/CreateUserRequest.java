package pl.kamilwojcik.passwordkeeper.users.api.dto;

import pl.kamilwojcik.passwordkeeper.validation.password.ValidAppPassword;
import pl.kamilwojcik.passwordkeeper.validation.username.ValidUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record CreateUserRequest(
        @ValidUsername String username,
        @Email String email,
        @ValidAppPassword String password,
        @ValidAppPassword String storagePassword
) {
}
