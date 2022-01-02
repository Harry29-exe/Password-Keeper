package pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests;

import org.hibernate.validator.constraints.URL;
import org.springframework.lang.Nullable;
import pl.kamilwojcik.passwordkeeper.validation.password.ValidAppPassword;
import pl.kamilwojcik.passwordkeeper.validation.password.specyfication.PasswordSpec;

import javax.validation.constraints.NotBlank;

public record CreateNewPasswordRequest(
        @ValidAppPassword String storagePassword,
        @NotBlank String passwordName,
        @URL String passwordUrl,
        @Nullable PasswordSpec passwordSpec
) {}
