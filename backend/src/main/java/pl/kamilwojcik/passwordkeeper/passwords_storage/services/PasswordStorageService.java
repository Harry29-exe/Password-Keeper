package pl.kamilwojcik.passwordkeeper.passwords_storage.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import pl.kamilwojcik.passwordkeeper.passwords_storage.services.dto.AddNewPassword;
import pl.kamilwojcik.passwordkeeper.passwords_storage.services.dto.UsePassword;

import java.util.UUID;

@Service
public interface PasswordStorageService {

    @PreAuthorize("@authorizationUtils.usernamesMatch(#username)")
    void savePassword(String passwordToSave, String passwordStorageKey, String username);

    @PreAuthorize("@authorizationUtils.usernamesMatch(#username)")
    String readPassword(String passwordName, String storageKayString, String username);

    @PreAuthorize("@authorizationUtils.usernamesMatch(#username)")
    void deletePassword(String passwordName, String username);

}
