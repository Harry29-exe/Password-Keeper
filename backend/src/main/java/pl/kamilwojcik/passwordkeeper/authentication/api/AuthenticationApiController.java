package pl.kamilwojcik.passwordkeeper.authentication.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;
import pl.kamilwojcik.passwordkeeper.authentication.api.dto.LoginRequest;
import pl.kamilwojcik.passwordkeeper.authentication.services.AuthenticationService;
import pl.kamilwojcik.passwordkeeper.authentication.services.JwtService;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.ClientDeviceService;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.AuthenticationException;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.DeviceNotAuthorizedException;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.UnknownDeviceException;
import pl.kamilwojcik.passwordkeeper.exceptions.request.NoRequiredCookieException;
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

    public static final String REFRESH_TOKEN_COOKIE_NAME = "Refresh-Token";
    public static final String REFRESH_TOKEN_PATH = "/api/refresh";
    private final Integer refreshTokenExpiresTimeInSec;

    public AuthenticationApiController(
            AuthenticationService authService,
            UserDetailsService userDetailsService,
            ClientDeviceService clientDeviceService,
            JwtService jwtService,
            @Value("${jwt.config.refresh.exp_in_sec}") Integer refreshExp
    ) throws NoSuchAlgorithmException {
        this.authService = authService;
        this.userDetailsService = userDetailsService;
        this.clientDeviceService = clientDeviceService;
        this.jwtService = jwtService;
        this.refreshTokenExpiresTimeInSec = refreshExp;
    }

    //todo add login event logging
    @Override
    public void login(Boolean dontLogout, LoginRequest requestBody, HttpServletRequest request, HttpServletResponse response) {

        try {
            authService.authenticate(requestBody.username(), requestBody.password());
        } catch (UnknownDeviceException | DeviceNotAuthorizedException ex) {
            clientDeviceService.addNewDeviceAuthorizationRequest(requestBody.username());
            throw ex;
        }

        var user = userDetailsService.loadUserByUsername(requestBody.username());


        var authToken = jwtService.createAuthToken(user);
        response.setHeader("Authorization", authToken);

        var refreshToken = jwtService.createRefreshToken(user);
        this.addRefreshTokenCookie(refreshToken, response, !dontLogout);

        this.delayLogin();
    }

    @Override
    public void logout(HttpServletResponse response) {
        var cookie = ResponseCookie
                .from(REFRESH_TOKEN_COOKIE_NAME, "")
                .httpOnly(true)
                .path(REFRESH_TOKEN_PATH)
                .httpOnly(true)
                .secure(true)
                .maxAge(0L)
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
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

        addRefreshTokenCookie(newRefreshToken, response, false);
    }

    private Cookie getRefreshToken(HttpServletRequest request) {
        var cookies = request.getCookies();
        if (cookies == null) {
            throw new NoRequiredCookieException(REFRESH_TOKEN_COOKIE_NAME);
        }

        var refreshToken = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(REFRESH_TOKEN_COOKIE_NAME))
                .findFirst()
                .orElseThrow(() -> new NoRequiredHeaderException(REFRESH_TOKEN_COOKIE_NAME));

        if(refreshToken == null ||
                refreshToken.getValue() == null ||
                refreshToken.getValue().isBlank()
        ) {
            throw new NoRequiredCookieException(REFRESH_TOKEN_COOKIE_NAME);
        }

        return refreshToken;
    }

    private void addRefreshTokenCookie(
            String refreshToken,
            HttpServletResponse response,
            boolean shouldExpireAtSessionEnd
    ) {
        var cookie = ResponseCookie.from(REFRESH_TOKEN_COOKIE_NAME, refreshToken)
                .httpOnly(true)
                .path(REFRESH_TOKEN_PATH)
                .secure(true)
                .sameSite("None")
                .maxAge(shouldExpireAtSessionEnd? -1: refreshTokenExpiresTimeInSec)
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
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
