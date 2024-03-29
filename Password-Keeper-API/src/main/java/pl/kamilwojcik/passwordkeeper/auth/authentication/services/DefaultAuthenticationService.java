package pl.kamilwojcik.passwordkeeper.auth.authentication.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.kamilwojcik.passwordkeeper.auth.account_lock.sevices.AccountLockService;
import pl.kamilwojcik.passwordkeeper.auth.authorized_devices.services.ClientDeviceService;
import pl.kamilwojcik.passwordkeeper.auth.authorized_devices.services.UserAgentService;
import pl.kamilwojcik.passwordkeeper.auth.authorized_devices.services.dto.CreateLoginEvent;
import pl.kamilwojcik.passwordkeeper.auth.loggingEvent.domain.LoginEvent.LoginEventResult;
import pl.kamilwojcik.passwordkeeper.auth.loggingEvent.services.LoginEventService;
import pl.kamilwojcik.passwordkeeper.config.security.honeypots.HoneypotActionType;
import pl.kamilwojcik.passwordkeeper.config.security.honeypots.HoneypotsAccountList;
import pl.kamilwojcik.passwordkeeper.config.security.honeypots.HoneypotsMsgDispatcher;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.authetication.AuthenticationException;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.authetication.DeviceNotAuthorizedException;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.authetication.UnknownDeviceException;
import pl.kamilwojcik.passwordkeeper.users.services.UserService;
import pl.kamilwojcik.passwordkeeper.utils.CurrentRequestUtils;

@Service
public class DefaultAuthenticationService implements AuthenticationService {
    private final ClientDeviceService clientDeviceService;
    private final UserAgentService userAgentService;
    private final UserService userService;
    private final LoginEventService loginEventService;
    private final AccountLockService accountLockService;

    private final AuthenticationManager authManager;

    private final HoneypotsAccountList honeypotsAccounts;
    private final HoneypotsMsgDispatcher honeypotsMsg;

    public DefaultAuthenticationService(
            ClientDeviceService clientDeviceService,
            UserAgentService userAgentService, UserService userService, LoginEventService loginEventService, AccountLockService accountLockService, AuthenticationManager authManager,
            HoneypotsAccountList honeypotsAccounts,
            HoneypotsMsgDispatcher honeypotsMsg) {
        this.clientDeviceService = clientDeviceService;
        this.userAgentService = userAgentService;
        this.userService = userService;
        this.loginEventService = loginEventService;
        this.accountLockService = accountLockService;
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


        } catch (Exception ex) {
            LoginEventResult failureCase =
                    ex instanceof BadCredentialsException ?
                            LoginEventResult.FAILURE_CREDENTIALS
                            :
                            ex instanceof DeviceNotAuthorizedException || ex instanceof UnknownDeviceException ?
                                    LoginEventResult.FAILURE_CLIENT_DEVICE
                                    :
                                    LoginEventResult.FAILURE_GENERAL;


            createLoginEvent(failureCase, username);
            accountLockService.handleUnsuccessfulLogin(username);
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

        var currentRequest = CurrentRequestUtils.getCurrentRequest();
        var address = CurrentRequestUtils.getPreProxyIp();
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
        var clientDevice =
                clientDeviceService.getCurrentDevice(username);

        CreateLoginEvent loginEvent = clientDevice.map(
                clientDeviceDTO -> new CreateLoginEvent(
                        eventResult,
                        clientDeviceDTO.getPublicId(),
                        CurrentRequestUtils.getPreProxyIp(),
                        username
                )).orElseGet(
                () -> new CreateLoginEvent(
                        eventResult,
                        userAgentService.parseToStorageForm(CurrentRequestUtils.getHeader("User-Agent")),
                        CurrentRequestUtils.getPreProxyIp(),
                        username
                ));

        loginEventService.addNewLoggingEvent(loginEvent);
    }

}
