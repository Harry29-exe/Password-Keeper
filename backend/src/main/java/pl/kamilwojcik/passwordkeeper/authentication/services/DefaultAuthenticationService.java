package pl.kamilwojcik.passwordkeeper.authentication.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.ClientDeviceService;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.UserAgentService;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.dto.CreateLoginEvent;
import pl.kamilwojcik.passwordkeeper.authorized.loggingEvent.domain.LoginEvent.LoginEventResult;
import pl.kamilwojcik.passwordkeeper.authorized.loggingEvent.services.LoginEventService;
import pl.kamilwojcik.passwordkeeper.config.security.honeypots.HoneypotActionType;
import pl.kamilwojcik.passwordkeeper.config.security.honeypots.HoneypotsAccountList;
import pl.kamilwojcik.passwordkeeper.config.security.honeypots.HoneypotsMsgDispatcher;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.AuthenticationException;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.DeviceNotAuthorizedException;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.UnknownDeviceException;
import pl.kamilwojcik.passwordkeeper.users.services.UserService;
import pl.kamilwojcik.passwordkeeper.utils.RequestUtils;

@Service
public class DefaultAuthenticationService implements AuthenticationService {
    private final ClientDeviceService clientDeviceService;
    private final UserAgentService userAgentService;
    private final UserService userService;
    private final LoginEventService loginEventService;

    private final AuthenticationManager authManager;

    private final HoneypotsAccountList honeypotsAccounts;
    private final HoneypotsMsgDispatcher honeypotsMsg;

    public DefaultAuthenticationService(
            ClientDeviceService clientDeviceService,
            UserAgentService userAgentService, UserService userService, LoginEventService loginEventService, AuthenticationManager authManager,
            HoneypotsAccountList honeypotsAccounts,
            HoneypotsMsgDispatcher honeypotsMsg) {
        this.clientDeviceService = clientDeviceService;
        this.userAgentService = userAgentService;
        this.userService = userService;
        this.loginEventService = loginEventService;
        this.authManager = authManager;
        this.honeypotsAccounts = honeypotsAccounts;
        this.honeypotsMsg = honeypotsMsg;
    }

    @Override
    public Authentication authenticate(String username, String password) {
        Authentication auth;
        try {
            auth = performAuthentication(username, password);
            createLoginEvent(LoginEventResult.SUCCESS, username);

        } catch (BadCredentialsException ex) {
            createLoginEvent(LoginEventResult.FAILURE_CREDENTIALS, username);
            throw ex;

        } catch (DeviceNotAuthorizedException | UnknownDeviceException ex) {
            createLoginEvent(LoginEventResult.FAILURE_CLIENT_DEVICE, username);
            throw ex;

        } catch (Exception ex) {
            createLoginEvent(LoginEventResult.FAILURE_GENERAL, username);
            throw ex;
        }

        return auth;
    }

    private Authentication performAuthentication(String username, String password) {
        if (!userService.exist(username)) {
            throw new AuthenticationException();
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        if (honeypotsAccounts.isHoneypot(auth)) {
            honeypotsMsg.dispatchMsg(auth, HoneypotActionType.LOGIN_ATTEMPT);
        }

        var currentRequest = RequestUtils.getCurrentRequest();
        var address = currentRequest.getRemoteAddr();
        var client = currentRequest.getHeader("User-Agent");

        boolean isDeviceAuthorized = clientDeviceService.findByDeviceConstraint(
                        address,
                        client,
                        username)
                .orElseThrow(UnknownDeviceException::new)
                .getIsAuthorized();

        if (!isDeviceAuthorized) {
            throw new DeviceNotAuthorizedException();
        }


        auth = authManager.authenticate(auth);
        if (honeypotsAccounts.isHoneypot(auth)) {
            honeypotsMsg.dispatchMsg(auth, HoneypotActionType.SUCCESSFUL_LOGIN);
        }


        return auth;
    }


    private void createLoginEvent(LoginEventResult eventResult, String username) {
        var request = RequestUtils.getCurrentRequest();
        var clientDevice =
                clientDeviceService.getCurrentDevice(username);

        CreateLoginEvent loginEvent = clientDevice.map(
                clientDeviceDTO -> new CreateLoginEvent(
                        eventResult,
                        clientDeviceDTO.getPublicId(),
                        request.getRemoteAddr(),
                        username
                )).orElseGet(
                () -> new CreateLoginEvent(
                        eventResult,
                        userAgentService.parseToStorageForm(request.getHeader("User-Agent")),
                        request.getRemoteAddr(),
                        username
                ));

        loginEventService.addNewLoggingEvent(loginEvent);
    }

}
