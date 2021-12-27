package pl.kamilwojcik.passwordkeeper.passwords_storage.services;

import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import pl.kamilwojcik.passwordkeeper.config.security.annotations.UsernameAndStoragePasswordMatch;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordInfoDto;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordRequirements;

import java.util.List;

public interface PasswordStorageService {

    @UsernameAndStoragePasswordMatch
    void encryptAndSave(String passwordToSave,
                        PasswordInfoDto passwordInfo,
                        String storagePassword,
                        String username);

    @UsernameAndStoragePasswordMatch
    String createNewPassword(PasswordInfoDto passwordInfo,
                           @Nullable PasswordRequirements requirements,
                           String storagePassword,
                           String username);

    @UsernameAndStoragePasswordMatch
    String readPassword(String passwordName,
                        String storagePassword,
                        String username);

    @PreAuthorize("@authFunctions.usernamesMatch(#username)")
    List<PasswordInfoDto> getUsersPasswords(String username);

    @PreAuthorize("@authFunctions.usernamesMatch(#username)")
    void deletePassword(String passwordName, String username);

}
