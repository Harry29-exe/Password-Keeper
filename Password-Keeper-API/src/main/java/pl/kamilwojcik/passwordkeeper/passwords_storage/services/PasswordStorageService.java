package pl.kamilwojcik.passwordkeeper.passwords_storage.services;

import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordInfoDto;
import pl.kamilwojcik.passwordkeeper.validation.password.specyfication.PasswordSpec;

import java.util.List;

public interface PasswordStorageService {

    @PreAuthorize("@authFunctions.usernamesMatch(#username) and @authFunctions.storagePasswordMatch(#storagePassword, #username)")
    void encryptAndSave(String passwordToSave,
                        PasswordInfoDto passwordInfo,
                        String storagePassword,
                        String username);

    @PreAuthorize("@authFunctions.usernamesMatch(#username) and @authFunctions.storagePasswordMatch(#storagePassword, #username)")
    String createNewPassword(PasswordInfoDto passwordInfo,
                             @Nullable PasswordSpec requirements,
                             String storagePassword,
                             String username);

    @PreAuthorize("@authFunctions.usernamesMatch(#username) and @authFunctions.storagePasswordMatch(#storagePassword, #username)")
    String readPassword(String passwordName,
                        String storagePassword,
                        String username);

    @PreAuthorize("@authFunctions.usernamesMatch(#username)")
    List<PasswordInfoDto> getUsersPasswords(String username);

    @PreAuthorize("@authFunctions.usernamesMatch(#username)")
    void deletePassword(String passwordName, String username);

}
