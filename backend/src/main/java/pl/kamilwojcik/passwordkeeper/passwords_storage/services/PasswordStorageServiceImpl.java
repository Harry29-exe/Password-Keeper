package pl.kamilwojcik.passwordkeeper.passwords_storage.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.passwords_storage.domain.repositories.PasswordRepository;
import pl.kamilwojcik.passwordkeeper.passwords_storage.services.dto.AddNewPassword;
import pl.kamilwojcik.passwordkeeper.passwords_storage.services.dto.UsePassword;

import java.util.UUID;

@Service
@Transactional
public class PasswordStorageServiceImpl implements PasswordStorageService {
    private final PasswordRepository passwordRepo;

    public PasswordStorageServiceImpl(PasswordRepository passwordRepo) {
        this.passwordRepo = passwordRepo;
    }

    @Override
    public void createFuturePasswords(String username, AddNewPassword newPassword) {

    }

    @Override
    public void usePassword(String username, UsePassword usePassword) {

    }

    @Override
    public void deletePassword(String username, String passwordName) {

    }

    @Override
    public String readPassword(String username, String storagePassword) {
        return null;
    }
}
