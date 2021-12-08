package pl.kamilwojcik.passwordkeeper.users.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.auth.dto.AppUser;
import pl.kamilwojcik.passwordkeeper.auth.dto.BasicAppUser;
import pl.kamilwojcik.passwordkeeper.exceptions.user.NoSuchUserException;
import pl.kamilwojcik.passwordkeeper.users.dao.entities.UserEntity;
import pl.kamilwojcik.passwordkeeper.users.dao.repositories.UserRepository;
import pl.kamilwojcik.passwordkeeper.users.services.dto.CreateUser;
import pl.kamilwojcik.passwordkeeper.validators.UserValidator;

import java.util.UUID;


@Service
@Transactional
public class UserServiceImpl implements UserService, AppUserService {
    private final UserRepository userRepo;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepo, UserValidator userValidator, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.userValidator = userValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser getByPublicId(UUID publicId) {
        var entity = userRepo.findByPublicId(publicId)
                .orElseThrow(NoSuchUserException::new);

        return new BasicAppUser(
                entity.getPublicId(),
                entity.getUsername(),
                entity.getPassword());
    }

    @Override
    public AppUser getByUsername(String username) {
        var entity = userRepo.findByUsername(username)
                .orElseThrow(NoSuchUserException::new);

        return new BasicAppUser(
                entity.getPublicId(),
                entity.getUsername(),
                entity.getPassword());
    }

    @Override
    public void createUser(CreateUser createUser) {
        userValidator.validateUsername(createUser.username());
        userValidator.validatePassword(createUser.nonEncodedPassword());
        userValidator.validatePassword(createUser.storageNonEncodedPassword());

        var userEntity = new UserEntity(
                createUser.username(),
                passwordEncoder.encode(createUser.nonEncodedPassword()),
                passwordEncoder.encode(createUser.storageNonEncodedPassword())
                );
        userRepo.save(userEntity);
    }


    @Override
    public void deleteUser(UUID publicId) {
        if(userRepo.existsByPublicId(publicId)) {
            userRepo.deleteByPublicId(publicId);
        } else {
            //todo
            throw new IllegalStateException();
        }
    }
}
