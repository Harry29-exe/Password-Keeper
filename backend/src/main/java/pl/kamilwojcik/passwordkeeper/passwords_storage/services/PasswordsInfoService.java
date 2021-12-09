package pl.kamilwojcik.passwordkeeper.passwords_storage.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordInfoDto;

import java.util.List;

@Service
public interface PasswordsInfoService {

    @PreAuthorize("#authentication.name == #username")
    List<PasswordInfoDto> getUsersPasswords(String username);

}
