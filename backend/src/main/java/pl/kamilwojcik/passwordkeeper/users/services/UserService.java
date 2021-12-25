package pl.kamilwojcik.passwordkeeper.users.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import pl.kamilwojcik.passwordkeeper.users.services.dto.CreateUser;

@Service
public interface UserService {

    void createUser(CreateUser createUser);

    @PreAuthorize("isAuthenticated() && #authentication.name == #username")
    void deleteUser(String username);

}
