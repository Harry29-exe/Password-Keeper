package pl.kamilwojcik.passwordkeeper.passwords_storage.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.passwords_storage.domain.repositories.PasswordRepository;
import pl.kamilwojcik.passwordkeeper.passwords_storage.services.dto.AddNewPassword;

import java.util.UUID;

@Service
@Transactional
public class PasswordStorageServiceImpl implements PasswordStorageService {
    private final PasswordRepository passwordRepo;

    public PasswordStorageServiceImpl(PasswordRepository passwordRepo) {
        this.passwordRepo = passwordRepo;
    }

    @Override
    public void createNewPassword(UUID userPubId, AddNewPassword newPassword) {

    }

    @Override
    public void deletePassword(UUID userPubId, String passwordName) {

    }

    @Override
    public String readPassword(UUID userPubId, String passwordName, String storagePassword) {
        return null;
    }
}
