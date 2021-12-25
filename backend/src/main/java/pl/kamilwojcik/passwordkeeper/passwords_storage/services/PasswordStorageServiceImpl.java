package pl.kamilwojcik.passwordkeeper.passwords_storage.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.passwords_storage.domain.PasswordRepository;
import pl.kamilwojcik.passwordkeeper.passwords_storage.services.dto.AddNewPassword;
import pl.kamilwojcik.passwordkeeper.passwords_storage.services.dto.UsePassword;

@Service
@Transactional
public class PasswordStorageServiceImpl implements PasswordStorageService {
    private final PasswordRepository passwordRepo;

    public PasswordStorageServiceImpl(PasswordRepository passwordRepo) {
        this.passwordRepo = passwordRepo;
    }

    @Override
    public void deletePassword(String username, String passwordName) {

    }

    @Override
    public void savePassword(String passwordToSave, String passwordStorageKey, String username) {

    }

    @Override
    public String readPassword(String passwordName, String storageKayString, String username) {
        return null;
    }
}
