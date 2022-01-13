package pl.kamilwojcik.passwordkeeper.auth.authentication.api;

import org.springframework.web.bind.annotation.*;
import pl.kamilwojcik.passwordkeeper.auth.authentication.api.dto.LoginRequest;
import pl.kamilwojcik.passwordkeeper.config.consts.CorsAddresses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@CrossOrigin(origins = CorsAddresses.FRONTEND_ADDRESS, exposedHeaders = "Authorization", allowCredentials = "true", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
public interface AuthenticationApi {

    @PostMapping("/login")
    void login(@RequestParam(defaultValue = "false") Boolean dontLogout, @RequestBody @Valid LoginRequest requestBody, HttpServletRequest request, HttpServletResponse response);

    @PostMapping("/logout")
    void logout(HttpServletResponse response);

    @PostMapping("/refresh/auth-token")
    void refreshAuthToken(HttpServletRequest request, HttpServletResponse response);

    @PostMapping("/refresh/refresh-token")
    void refreshRefreshToken(HttpServletRequest request, HttpServletResponse response);

}
