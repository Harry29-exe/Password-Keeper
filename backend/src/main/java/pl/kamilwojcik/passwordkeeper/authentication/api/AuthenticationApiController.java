package pl.kamilwojcik.passwordkeeper.authentication.api;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;
import pl.kamilwojcik.passwordkeeper.authentication.dto.value.LoginRequest;
import pl.kamilwojcik.passwordkeeper.authentication.services.JwtService;
import pl.kamilwojcik.passwordkeeper.config.security.honeypots.HoneypotActionType;
import pl.kamilwojcik.passwordkeeper.config.security.honeypots.HoneypotsAccountList;
import pl.kamilwojcik.passwordkeeper.config.security.honeypots.HoneypotsMsgDispatcher;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.AuthenticationException;
import pl.kamilwojcik.passwordkeeper.validation.UserValidator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@RestController
public class AuthenticationApiController implements AuthenticationApi {
    private final AuthenticationManager authManager;
    private final UserDetailsService userDetailsService;
    private final UserValidator userValidator;
    private final JwtService jwtService;

    private final HoneypotsAccountList honeypotsAccounts;
    private final HoneypotsMsgDispatcher honeypotsMsg;

    private final SecureRandom secureRandom = SecureRandom.getInstanceStrong();

    public AuthenticationApiController(AuthenticationManager authManager,
                                       UserDetailsService userDetailsService,
                                       UserValidator userValidator,
                                       JwtService jwtService,
                                       HoneypotsAccountList honeypotsAccounts, HoneypotsMsgDispatcher honeypotsMsg) throws NoSuchAlgorithmException {
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
        this.userValidator = userValidator;
        this.jwtService = jwtService;
        this.honeypotsAccounts = honeypotsAccounts;
        this.honeypotsMsg = honeypotsMsg;
    }

    @Override
    public void login(LoginRequest request, HttpServletResponse response) {
        userValidator.validateUsername(request.username());
        userValidator.validatePassword(request.password());

        Authentication auth = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        if(honeypotsAccounts.isHoneypot(auth)) {
            honeypotsMsg.dispatchMsg(auth, HoneypotActionType.LOGIN_ATTEMPT);
        }

        auth = authManager.authenticate(auth);
        if(honeypotsAccounts.isHoneypot(auth)) {
            honeypotsMsg.dispatchMsg(auth, HoneypotActionType.SUCCESSFUL_LOGIN);
        }

        var user = userDetailsService.loadUserByUsername(request.username());


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
