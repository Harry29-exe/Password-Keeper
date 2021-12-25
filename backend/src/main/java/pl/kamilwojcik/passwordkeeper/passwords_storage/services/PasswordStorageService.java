package pl.kamilwojcik.passwordkeeper.passwords_storage.services;

import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordInfoDto;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordRequirements;

@Service
public interface PasswordStorageService {

    @PreAuthorize("@authorizationUtils.usernamesMatch(#username)")
    void savePassword(String passwordToSave,
                      PasswordInfoDto passwordInfo,
                      String storagePassword,
                      String username);

    @PreAuthorize("@authorizationUtils.usernamesMatch(#username)")
    void createNewPassword(PasswordInfoDto passwordInfo,
                           @Nullable PasswordRequirements requirements,
                           String storagePassword,
                           String username);

    @PreAuthorize("@authorizationUtils.usernamesMatch(#username)")
    String readPassword(String passwordName,
                        String storageKayString,
                        String username);

    @PreAuthorize("@authorizationUtils.usernamesMatch(#username)")
    void deletePassword(String passwordName, String username);

}
