package pl.kamilwojcik.passwordkeeper.passwords_storage.api;

import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests.CreateNewSecurePassword;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests.DecodeAndGetRequest;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests.SavePasswordRequest;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordInfoDto;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequestMapping("password-storage")
public interface PasswordApi {

    @PostMapping("get")
    void decodeAndGetPassword(
            @RequestBody @Valid DecodeAndGetRequest request,
            Authentication auth
    );


    @PutMapping("save-password")
    void saveNewPassword(
            @RequestBody @Valid SavePasswordRequest request,
            Authentication auth
    );


    @PutMapping("create-secure-password")
    void createSecurePassword(
            @RequestBody @Valid CreateNewSecurePassword request,
            Authentication auth
    );

    @GetMapping
    List<PasswordInfoDto> getAllPasswordsInStorage(
            Authentication auth
    );


}
