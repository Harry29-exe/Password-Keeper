package pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests;

import org.hibernate.validator.constraints.URL;
import org.springframework.lang.Nullable;
import pl.kamilwojcik.passwordkeeper.validation.password.app.AppPassword;
import pl.kamilwojcik.passwordkeeper.validation.password.specyfication.PasswordSpec;
import pl.kamilwojcik.passwordkeeper.validation.text_or_digit.SimpleText;

public record CreateNewPasswordRequest(
        @AppPassword String storagePassword,
        @SimpleText String passwordName,
        @URL String passwordUrl,
        @Nullable PasswordSpec passwordSpec
) {
}
