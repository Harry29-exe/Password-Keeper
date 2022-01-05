package pl.kamilwojcik.passwordkeeper.users.api;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kamilwojcik.passwordkeeper.users.api.dto.CreateUserRequest;
import pl.kamilwojcik.passwordkeeper.users.api.dto.DeleteUserRequest;

import javax.validation.Valid;

@Validated
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public interface UserApi {

    @PutMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    void registerNewUser(@RequestBody @Valid CreateUserRequest request);

    @DeleteMapping("delete")
    void deleteUser(@RequestBody @Valid DeleteUserRequest request);


}
