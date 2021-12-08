package pl.kamilwojcik.passwordkeeper.auth.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kamilwojcik.passwordkeeper.auth.api.dto.LoginRequest;

@RequestMapping
public interface AuthenticationApi {

    @PostMapping("/login")
    void login(LoginRequest request);

}
