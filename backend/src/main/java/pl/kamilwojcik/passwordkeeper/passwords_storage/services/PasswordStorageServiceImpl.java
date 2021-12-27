package pl.kamilwojcik.passwordkeeper.passwords_storage.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.passwords_storage.domain.PasswordEntity;
import pl.kamilwojcik.passwordkeeper.passwords_storage.domain.PasswordRepository;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordInfoDto;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordRequirements;
import pl.kamilwojcik.passwordkeeper.passwords_storage.services.components.SecurePasswordCreator;
import pl.kamilwojcik.passwordkeeper.passwords_storage.services.components.PasswordStorageCypher;
import pl.kamilwojcik.passwordkeeper.users.domain.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class PasswordStorageServiceImpl implements PasswordStorageService {
    private final PasswordRepository passwordRepo;
    private final UserRepository userRepo;
    private final PasswordStorageCypher storageCypher;
    private final SecurePasswordCreator securePasswordCreator;

    public PasswordStorageServiceImpl(PasswordRepository passwordRepo, UserRepository userRepo, PasswordStorageCypher storageCypher, SecurePasswordCreator securePasswordCreator) {
        this.passwordRepo = passwordRepo;
        this.userRepo = userRepo;
        this.storageCypher = storageCypher;
        this.securePasswordCreator = securePasswordCreator;
    }


    @Override
    public void encryptAndSave(String passwordToSave,
                               PasswordInfoDto passwordInfo,
                               String storagePassword,
                               String username
    ) {
        var iv = storageCypher.generateInputVector();
        var salt = storageCypher.generateSalt();
        var key = storageCypher.generateStorageKey(storagePassword, salt);
        var encryptedPassword = storageCypher.encryptPassword(
                passwordToSave,
                key,
                iv
        );

        var user = userRepo.getByUsername(username)
                .orElseThrow(EntityNotFoundException::new);
        PasswordEntity entity = new PasswordEntity(
                encryptedPassword,
                passwordInfo.getPasswordName(),
                passwordInfo.getPasswordUrl(),
                iv,
                salt,
                user
        );
        passwordRepo.save(entity);
    }


    @Override
    public String createNewPassword(PasswordInfoDto passwordInfo,
                                  PasswordRequirements requirements,
                                  String storagePassword,
                                  String username
    ) {
        String passwordToSave;
        if (requirements == null) {
            passwordToSave = securePasswordCreator.createSecurePassword();
        } else {
            passwordToSave = securePasswordCreator.createSecurePassword(requirements);
        }

        this.encryptAndSave(passwordToSave, passwordInfo, storagePassword, username);
        return passwordToSave;
    }


    @Override
    public String readPassword(String passwordName,
                               String storagePassword,
                               String username
    ) {
        var passwordEntity = passwordRepo.findByUser_UsernameAndPasswordName(
                username, passwordName
        );
        var storageKey = storageCypher.generateStorageKey(
                storagePassword,
                passwordEntity.getSalt()
        );

        return storageCypher.decodePassword(
                passwordEntity.getEncryptedPassword(),
                storageKey,
                passwordEntity.getIv()
        );
    }


    @Override
    public List<PasswordInfoDto> getUsersPasswords(String username) {

        return passwordRepo.findDtoByUser_Username(username);
    }


    @Override
    public void deletePassword(String username, String passwordName) {
        if (passwordRepo.existsByUser_UsernameAndPasswordName(username, passwordName)) {
            passwordRepo.deleteByUser_UsernameAndPasswordName(username, passwordName);
        } else {
            throw new EntityNotFoundException("Given password does not exists");
        }
    }


}
