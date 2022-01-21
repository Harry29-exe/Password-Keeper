package pl.kamilwojcik.passwordkeeper.auth.authentication.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kamilwojcik.passwordkeeper.auth.authentication.api.dto.LoginRequest;
import pl.kamilwojcik.passwordkeeper.config.DefaultCors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@DefaultCors(
        exposedHeaders = "Authorization",
        allowCredentials = "true",
        methods = {RequestMethod.POST, RequestMethod.OPTIONS})
public interface AuthenticationApi {

    @PostMapping("/login")
    void login(@RequestParam(defaultValue = "false") Boolean dontLogout,
               @RequestBody @Valid LoginRequest requestBody,
               HttpServletRequest request,
               HttpServletResponse response);

    @PostMapping("/logout")
    void logout(HttpServletResponse response);

    @PostMapping("/refresh/auth-token")
    void refreshAuthToken(HttpServletRequest request, HttpServletResponse response);

    @PostMapping("/refresh/refresh-token")
    void refreshRefreshToken(HttpServletRequest request, HttpServletResponse response);

}
