package pl.kamilwojcik.passwordkeeper.users.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import pl.kamilwojcik.passwordkeeper.users.api.dto.CreateUserRequest;
import pl.kamilwojcik.passwordkeeper.users.api.dto.DeleteUserRequest;
import pl.kamilwojcik.passwordkeeper.users.services.UserService;
import pl.kamilwojcik.passwordkeeper.users.services.dto.CreateUser;

@RestController
public class UserApiImpl implements UserApi {
    private final UserService userService;

    public UserApiImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void registerNewUser(CreateUserRequest request) {
        userService.createUser(
                new CreateUser(
                        request.username(),
                        request.email(),
                        request.password(),
                        request.storagePassword()
                ));
    }

    @Override
    public void deleteUser(DeleteUserRequest request) {
        userService.deleteUser(request.username());
    }

}
