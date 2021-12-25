package pl.kamilwojcik.passwordkeeper.passwords_storage.api;

import org.springframework.security.core.Authentication;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests.CreateNewPasswordRequest;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests.DecodeAndGetRequest;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests.DeletePasswordRequest;
import pl.kamilwojcik.passwordkeeper.passwords_storage.api.requests.SavePasswordRequest;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordInfoDto;
import pl.kamilwojcik.passwordkeeper.passwords_storage.services.PasswordStorageService;

import java.util.List;

public class PasswordApiController implements PasswordApi {
    private final PasswordStorageService passwordStorageService;

    public PasswordApiController(PasswordStorageService passwordStorageService) {
        this.passwordStorageService = passwordStorageService;
    }


    @Override
    public void saveNewPassword(SavePasswordRequest request, Authentication auth) {
        passwordStorageService.savePassword(
                request.passwordToSave(),
                new PasswordInfoDto(request.passwordName(), request.passwordUrl()),
                request.storagePassword(),
                auth.getName()
        );
    }

    @Override
    public String createSecurePassword(CreateNewPasswordRequest request, Authentication auth) {
        return passwordStorageService.createNewPassword(
                new PasswordInfoDto(request.passwordName(), request.passwordUrl()),
                request.passwordRequirements(),
                request.storagePassword(),
                auth.getName()
        );
    }

    @Override
    public String decodeAndGetPassword(DecodeAndGetRequest request, Authentication auth) {
        return passwordStorageService.readPassword(
                request.passwordName(),
                request.storagePassword(),
                auth.getName());
    }

    @Override
    public void deletePassword(DeletePasswordRequest request, Authentication auth) {
        passwordStorageService.deletePassword(request.passwordName(), auth.getName());
    }

    @Override
    public List<PasswordInfoDto> getAllPasswordsInStorage(Authentication auth) {
        return null;
    }
}
