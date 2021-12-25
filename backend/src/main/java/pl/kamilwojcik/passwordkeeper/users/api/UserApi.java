package pl.kamilwojcik.passwordkeeper.users.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kamilwojcik.passwordkeeper.users.api.dto.DeleteUserRequest;
import pl.kamilwojcik.passwordkeeper.users.services.dto.CreateUser;

@RequestMapping("/user")
public interface UserApi {

    @PostMapping("/register")
    void registerNewUser(CreateUser request);

    @DeleteMapping("delete")
    void deleteUser(DeleteUserRequest request);


}
