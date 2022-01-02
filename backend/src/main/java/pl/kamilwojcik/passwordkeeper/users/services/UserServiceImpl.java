package pl.kamilwojcik.passwordkeeper.users.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.users.domain.entities.UserEntity;
import pl.kamilwojcik.passwordkeeper.users.domain.repositories.UserRepository;
import pl.kamilwojcik.passwordkeeper.users.services.dto.CreateUser;
import pl.kamilwojcik.passwordkeeper.validation.UserValidator;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(CreateUser createUser) {
        var userEntity = new UserEntity(
                createUser.username(),
                createUser.email(),
                passwordEncoder.encode(createUser.nonEncodedPassword()),
                passwordEncoder.encode(createUser.storageNonEncodedPassword())
        );

        userRepo.save(userEntity);
    }


    @Override
    public void deleteUser(String username) {
        if (userRepo.existsByUsername(username)) {
            userRepo.deleteByUsername(username);
        } else {
            //todo
            throw new IllegalStateException();
        }
    }
}
