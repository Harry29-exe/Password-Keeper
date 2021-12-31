package pl.kamilwojcik.passwordkeeper.authentication.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kamilwojcik.passwordkeeper.authentication.dto.value.LoginRequest;
import pl.kamilwojcik.passwordkeeper.config.consts.CorsAddresses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequestMapping
@Validated
@CrossOrigin(origins = CorsAddresses.FRONTEND_ADDRESS, exposedHeaders = "Authorization", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
public interface AuthenticationApi {

    @PostMapping("/login")
    void login(@RequestBody @Valid LoginRequest request, HttpServletResponse response);

    @GetMapping("/logout")
    void logout(HttpServletResponse response);

    @PostMapping("/refresh/auth-token")
    void refreshAuthToken(HttpServletRequest request, HttpServletResponse response);

    @PostMapping("/refresh/refresh-token")
    void refreshRefreshToken(HttpServletRequest request, HttpServletResponse response);

}
