package pl.kamilwojcik.passwordkeeper.users.api;

import pl.kamilwojcik.passwordkeeper.users.api.dto.DeleteUserRequest;
import pl.kamilwojcik.passwordkeeper.users.services.UserService;
import pl.kamilwojcik.passwordkeeper.users.services.dto.CreateUser;

public class UserApiImpl implements UserApi {
    private final UserService userService;

    public UserApiImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void registerNewUser(CreateUser request) {
        userService.createUser(request);
    }

    @Override
    public void deleteUser(DeleteUserRequest request) {
        userService.deleteUser(request.username());
    }

}
