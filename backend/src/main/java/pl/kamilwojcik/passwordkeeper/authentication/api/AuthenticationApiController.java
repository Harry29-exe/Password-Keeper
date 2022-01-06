package pl.kamilwojcik.passwordkeeper.authentication.api;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;
import pl.kamilwojcik.passwordkeeper.authentication.dto.value.LoginRequest;
import pl.kamilwojcik.passwordkeeper.authentication.services.AuthenticationService;
import pl.kamilwojcik.passwordkeeper.authentication.services.JwtService;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.ClientDeviceService;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.AuthenticationException;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.DeviceNotAuthorizedException;
import pl.kamilwojcik.passwordkeeper.exceptions.request.InvalidRequestException;
import pl.kamilwojcik.passwordkeeper.exceptions.request.NoRequiredHeaderException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@RestController
public class AuthenticationApiController implements AuthenticationApi {
    private final AuthenticationService authService;
    private final UserDetailsService userDetailsService;
    private final ClientDeviceService clientDeviceService;
    private final JwtService jwtService;

    private final SecureRandom secureRandom = SecureRandom.getInstanceStrong();

    private final String REFRESH_TOKEN_COOKIE_NAME = "Refresh-Token";
    private final String REFRESH_TOKEN_PATH = "/refresh";

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
        Cookie cookie = new Cookie(REFRESH_TOKEN_COOKIE_NAME, "");
        cookie.setPath("/refresh");
        cookie.setHttpOnly(true);
        //todo not for production
        cookie.setSecure(false);

        response.addCookie(cookie);
    }

    @Override
    public void refreshAuthToken(HttpServletRequest request, HttpServletResponse response) {
        var refreshToken = getRefreshToken(request);
        var newAuthToken = jwtService.refreshAuthToken(refreshToken.getValue());

        response.setHeader("Authorization", newAuthToken);
    }

    @Override
    public void refreshRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        var refreshToken = getRefreshToken(request);
        var newRefreshToken = jwtService.refreshRefreshToken(refreshToken.getValue());

        addRefreshTokenCookie(newRefreshToken, response);
    }

    private Cookie getRefreshToken(HttpServletRequest request) {
        var refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(REFRESH_TOKEN_COOKIE_NAME))
                .findFirst()
                .orElseThrow(() -> new NoRequiredHeaderException(REFRESH_TOKEN_COOKIE_NAME));

        validateRefreshTokenCookie(refreshToken);

        return refreshToken;
    }

    private void validateRefreshTokenCookie(Cookie cookie) {
        boolean isValid = cookie.getName().equals(REFRESH_TOKEN_COOKIE_NAME) &&
                cookie.getPath().equals(REFRESH_TOKEN_PATH) &&
                !cookie.getValue().isBlank() &&
                cookie.isHttpOnly() &&
                //todo not for production
                !cookie.getSecure();

        if (!isValid) {
            throw new InvalidRequestException();
        }
    }

    private void addRefreshTokenCookie(String refreshToken, HttpServletResponse response) {
        Cookie cookie = new Cookie("Refresh-Token", refreshToken);
        cookie.setPath(REFRESH_TOKEN_PATH);
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
