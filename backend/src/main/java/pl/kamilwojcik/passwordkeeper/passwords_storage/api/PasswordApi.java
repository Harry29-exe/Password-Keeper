package pl.kamilwojcik.passwordkeeper.passwords_storage.api;

import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests.CreateNewPasswordRequest;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests.DecodeAndGetRequest;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests.DeletePasswordRequest;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests.SavePasswordRequest;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordInfoDto;

//todo zobaczyć czy to działa
import javax.validation.Valid;
import java.util.List;

@Validated
@RequestMapping("password-storage")
public interface PasswordApi {


    @PutMapping("save-password")
    void saveNewPassword(
            @RequestBody @Valid SavePasswordRequest request,
            Authentication auth
    );


    @PutMapping("create-password")
    String createSecurePassword(
            @RequestBody @Valid CreateNewPasswordRequest request,
            Authentication auth
    );


    @PostMapping("get")
    String decodeAndGetPassword(
            @RequestBody @Valid DecodeAndGetRequest request,
            Authentication auth
    );


    //todo
//    @PatchMapping
//    void updatePasswordInfo()


    @DeleteMapping
    void deletePassword(
            @RequestBody @Valid DeletePasswordRequest request,
            Authentication auth
    );


    @GetMapping
    List<PasswordInfoDto> getAllPasswordsInStorage(
            Authentication auth
    );


}
