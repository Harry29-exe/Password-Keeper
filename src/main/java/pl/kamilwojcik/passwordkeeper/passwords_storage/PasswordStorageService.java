package pl.kamilwojcik.passwordkeeper.passwords_storage;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.values.AddNewPassword;

import java.util.UUID;

@Service
public interface PasswordStorageService {

    @PreAuthorize("authentication.principal == #userPubId")
    void addNewPassword(UUID userPubId, AddNewPassword newPassword);

    @PreAuthorize("authentication.principal == #userPubId")
    void deletePassword(UUID userPubId, String passwordName);

    @PreAuthorize("authentication.principal == #userPubId")
    String readPassword(UUID userPubId, String passwordName, String storagePassword);

}
