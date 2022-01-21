package pl.kamilwojcik.passwordkeeper.users.api;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kamilwojcik.passwordkeeper.config.DefaultCors;
import pl.kamilwojcik.passwordkeeper.users.api.dto.CreateUserRequest;
import pl.kamilwojcik.passwordkeeper.users.api.dto.DeleteUserRequest;

import javax.validation.Valid;

@DefaultCors
@Validated
@RequestMapping("/users")
@PreAuthorize("isAuthenticated()")
public interface UserApi {

    @PutMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    void registerNewUser(@RequestBody @Valid CreateUserRequest request);

    @DeleteMapping("delete")
    void deleteUser(@RequestBody @Valid DeleteUserRequest request);


}
