package pl.kamilwojcik.passwordkeeper.users.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.users.dao.entities.UserEntity;
import pl.kamilwojcik.passwordkeeper.users.dao.repositories.UserRepository;
import pl.kamilwojcik.passwordkeeper.users.services.dto.CreateUser;
import pl.kamilwojcik.passwordkeeper.validators.UserValidator;

import java.util.UUID;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepo, UserValidator userValidator, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.userValidator = userValidator;
        this.passwordEncoder = passwordEncoder;
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
    public void deleteUser(String username) {
        if(userRepo.existsByUsername(username)) {
            userRepo.deleteByUsername(username);
        } else {
            //todo
            throw new IllegalStateException();
        }
    }
}
