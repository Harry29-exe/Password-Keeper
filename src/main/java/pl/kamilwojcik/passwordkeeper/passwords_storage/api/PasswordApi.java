package pl.kamilwojcik.passwordkeeper.passwords_storage.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.dto.CreateNewPasswordRequest;

@RequestMapping("/password-storage")
public interface PasswordApi {

    @PostMapping
    void createNewPassword(@RequestBody @Validated CreateNewPasswordRequest request);

}
