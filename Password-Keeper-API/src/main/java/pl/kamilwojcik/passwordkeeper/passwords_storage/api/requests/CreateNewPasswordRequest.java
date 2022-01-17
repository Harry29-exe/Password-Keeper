package pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests;

import org.springframework.lang.Nullable;
import pl.kamilwojcik.passwordkeeper.validation.password.app.AppPassword;
import pl.kamilwojcik.passwordkeeper.validation.password.specyfication.PasswordSpec;
import pl.kamilwojcik.passwordkeeper.validation.text.SimpleText;
import pl.kamilwojcik.passwordkeeper.validation.text.UrlLike;

public record CreateNewPasswordRequest(
        @AppPassword String storagePassword,
        @SimpleText String passwordName,
        @UrlLike String passwordUrl,
        @Nullable PasswordSpec passwordSpec
) {
}
