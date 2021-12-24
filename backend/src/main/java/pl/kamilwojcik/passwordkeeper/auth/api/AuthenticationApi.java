package pl.kamilwojcik.passwordkeeper.auth.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kamilwojcik.passwordkeeper.auth.dto.value.LoginRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping
public interface AuthenticationApi {

    @PostMapping("/login")
    void login(LoginRequest request, HttpServletResponse response);

    @PostMapping("/refresh-auth-token")
    void refreshAuthToken(HttpServletRequest request, HttpServletResponse response);

    @PostMapping("/refresh-refresh-token")
    void refreshRefreshToken(HttpServletRequest request, HttpServletResponse response);

}
