package pl.kamilwojcik.passwordkeeper.authentication.api;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;
import pl.kamilwojcik.passwordkeeper.authentication.dto.value.LoginRequest;
import pl.kamilwojcik.passwordkeeper.authentication.services.AuthenticationService;
import pl.kamilwojcik.passwordkeeper.authentication.services.JwtService;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.ClientDeviceService;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.AuthenticationException;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.DeviceNotAuthorizedException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@RestController
public class AuthenticationApiController implements AuthenticationApi {
    private final AuthenticationService authService;
    private final UserDetailsService userDetailsService;
    private final ClientDeviceService clientDeviceService;
    private final JwtService jwtService;

    private final SecureRandom secureRandom = SecureRandom.getInstanceStrong();

    public AuthenticationApiController(
            AuthenticationService authService,
            UserDetailsService userDetailsService,
            ClientDeviceService clientDeviceService, JwtService jwtService) throws NoSuchAlgorithmException {
        this.authService = authService;
        this.userDetailsService = userDetailsService;
        this.clientDeviceService = clientDeviceService;
        this.jwtService = jwtService;
    }

    @Override
    public void login(LoginRequest requestBody, HttpServletRequest request, HttpServletResponse response) {

        try {
            authService.authenticate(requestBody.username(), requestBody.password());
        } catch (DeviceNotAuthorizedException ex) {
            clientDeviceService.addClientDevice(requestBody.username());
            throw new DeviceNotAuthorizedException();
        }

        var user = userDetailsService.loadUserByUsername(requestBody.username());


        var authToken = jwtService.createAuthToken(user);
        response.setHeader("Authorization", authToken);

        var refreshToken = jwtService.createRefreshToken(user);
        this.addRefreshTokenCookie(refreshToken, response);

        this.delayLogin();
    }

    @Override
    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("Refresh-Token", "");
        cookie.setPath("/refresh");
        cookie.setHttpOnly(true);
        //todo not for production
        cookie.setSecure(false);

        response.addCookie(cookie);
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
        cookie.setSecure(false);

        response.addCookie(cookie);
    }

    private void delayLogin() {
        var randomTimeout = secureRandom.nextInt(0, 201);

        try {
            TimeUnit.MILLISECONDS.sleep(400 + randomTimeout);
        } catch (InterruptedException ex) {
            throw new AuthenticationException();
        }

    }


}
