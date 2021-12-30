package pl.kamilwojcik.passwordkeeper.auth.api;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;
import pl.kamilwojcik.passwordkeeper.auth.dto.value.LoginRequest;
import pl.kamilwojcik.passwordkeeper.auth.services.JwtService;
import pl.kamilwojcik.passwordkeeper.validators.UserValidator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationApiController implements AuthenticationApi {
    private final AuthenticationManager authManager;
    private final UserDetailsService userDetailsService;
    private final UserValidator userValidator;
    private final JwtService jwtService;

    public AuthenticationApiController(AuthenticationManager authManager,
                                       UserDetailsService userDetailsService,
                                       UserValidator userValidator,
                                       JwtService jwtService
    ) {
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
        this.userValidator = userValidator;
        this.jwtService = jwtService;
    }

    @Override
    public void login(LoginRequest request, HttpServletResponse response) {
        userValidator.validateUsername(request.username());
        userValidator.validatePassword(request.password());

        Authentication auth = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        authManager.authenticate(auth);

        var user = userDetailsService.loadUserByUsername(request.username());


        var authToken = jwtService.createAuthToken(user);
        response.setHeader("Authorization", authToken);

        var refreshToken = jwtService.createRefreshToken(user);
        this.addRefreshTokenCookie(refreshToken, response);
    }

    @Override
    public void refreshAuthToken(HttpServletRequest request, HttpServletResponse response) {
        // TODO: 31.12.2021
    }

    @Override
    public void refreshRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        // TODO: 31.12.2021
    }

    private void validateCookie(Cookie cookie) {
        //todo
    }

    private void addRefreshTokenCookie(String refreshToken, HttpServletResponse response) {
        Cookie cookie = new Cookie("Refresh-Token", refreshToken);
        cookie.setPath("/refresh");
        cookie.setHttpOnly(true);
        //todo for production
        cookie.setSecure(true);

        response.addCookie(cookie);
    }

}
