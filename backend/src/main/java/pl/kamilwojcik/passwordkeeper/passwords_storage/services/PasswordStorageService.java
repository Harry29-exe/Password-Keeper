package pl.kamilwojcik.passwordkeeper.passwords_storage.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import pl.kamilwojcik.passwordkeeper.passwords_storage.services.dto.AddNewPassword;
import pl.kamilwojcik.passwordkeeper.passwords_storage.services.dto.UsePassword;

import java.util.UUID;

@Service
public interface PasswordStorageService {

    @PreAuthorize("authentication.name == #username")
    void createFuturePasswords(String username, AddNewPassword newPassword);

    @PreAuthorize("authentication.name == #username")
    void usePassword(String username, UsePassword usePassword);

    @PreAuthorize("authentication.name == #username")
    void deletePassword(String username, String passwordName);

    @PreAuthorize("authentication.name == #username")
    String readPassword(String username, String storagePassword);

}
