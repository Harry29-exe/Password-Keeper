package pl.kamilwojcik.passwordkeeper.users.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import pl.kamilwojcik.passwordkeeper.users.dto.values.CreateUser;

import java.util.UUID;

@Service
public interface UserService {

    void createUser(CreateUser createUser);

    @PreAuthorize("#authentication.principal == #publicId")
    void deleteUser(UUID publicId);

}
