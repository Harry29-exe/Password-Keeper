package pl.kamilwojcik.passwordkeeper.passwords_storage.api;

import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.dto.CreateFuturePasswordsRequest;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.dto.DecodeAndGetRequest;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.dto.UseFuturePasswordRequest;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordInfoDto;

import java.util.List;

@RequestMapping("/password-storage/")
public interface PasswordApi {

    @PostMapping
    void useFuturePassword(@RequestBody @Validated UseFuturePasswordRequest request);

    @PutMapping
    void createFuturePasswords(@RequestBody @Validated CreateFuturePasswordsRequest request);

    @PostMapping("get")
    void decodeAndGetPassword(@RequestBody @Validated DecodeAndGetRequest request);

    @GetMapping
    List<PasswordInfoDto> getAllPasswordsInStorage(Authentication auth);


}
