package pl.kamilwojcik.passwordkeeper.passwords_storage.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.kamilwojcik.passwordkeeper.config.DefaultCors;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests.CreateNewPasswordRequest;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests.DecodeAndGetRequest;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests.DeletePasswordRequest;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests.SavePasswordRequest;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordInfoDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@DefaultCors
@RequestMapping("password-storage")
@PreAuthorize("isAuthenticated()")
public interface PasswordApi {

    @PutMapping("save-password")
    void saveNewPassword(
            @RequestBody @Valid SavePasswordRequest request,
            @NotNull Authentication auth
    );


    @PutMapping("create-password")
    String createSecurePassword(
            @RequestBody @Valid CreateNewPasswordRequest request,
            @NotNull Authentication auth
    );

    @PostMapping("get")
    String decodeAndGetPassword(
            @RequestBody
            @Valid DecodeAndGetRequest request,
            @NotNull Authentication auth
    );

    @GetMapping
    List<PasswordInfoDto> getAllPasswordsInStorage(
            @NotNull Authentication auth
    );

    @DeleteMapping
    void deletePassword(
            @RequestBody @Valid DeletePasswordRequest request,
            @NotNull Authentication auth
    );
}
