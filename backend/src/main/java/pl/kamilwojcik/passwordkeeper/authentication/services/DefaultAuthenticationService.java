package pl.kamilwojcik.passwordkeeper.authentication.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.AuthorizedDeviceRepoService;
import pl.kamilwojcik.passwordkeeper.config.security.honeypots.HoneypotActionType;
import pl.kamilwojcik.passwordkeeper.config.security.honeypots.HoneypotsAccountList;
import pl.kamilwojcik.passwordkeeper.config.security.honeypots.HoneypotsMsgDispatcher;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.AuthenticationException;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.DeviceNotAuthorizedException;
import pl.kamilwojcik.passwordkeeper.users.services.UserService;

import javax.servlet.http.HttpServletRequest;

@Service
public class DefaultAuthenticationService implements AuthenticationService {
    private final AuthorizedDeviceRepoService authorizedDeviceService;
    private final UserService userService;
    private final AuthenticationManager authManager;
    private final HoneypotsAccountList honeypotsAccounts;
    private final HoneypotsMsgDispatcher honeypotsMsg;

    public DefaultAuthenticationService(
            AuthorizedDeviceRepoService authorizedDeviceService,
            UserService userService, AuthenticationManager authManager,
            HoneypotsAccountList honeypotsAccounts,
            HoneypotsMsgDispatcher honeypotsMsg) {
        this.authorizedDeviceService = authorizedDeviceService;
        this.userService = userService;
        this.authManager = authManager;
        this.honeypotsAccounts = honeypotsAccounts;
        this.honeypotsMsg = honeypotsMsg;
    }


    @Override
    public Authentication authenticate(String username, String password) {
        if(!userService.exist(username)) {
            throw new AuthenticationException();
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        if(honeypotsAccounts.isHoneypot(auth)) {
            honeypotsMsg.dispatchMsg(auth, HoneypotActionType.LOGIN_ATTEMPT);
        }

        var address = getRequest().getRemoteAddr();
        var client = getRequest().getHeader("User-Agent");

        boolean isDeviceAuthorized = authorizedDeviceService.authorizedDeviceExists(
                address,
                client,
                username
        );

        if(!isDeviceAuthorized) {
            throw new DeviceNotAuthorizedException();
        }


        auth = authManager.authenticate(auth);
        if(honeypotsAccounts.isHoneypot(auth)) {
            honeypotsMsg.dispatchMsg(auth, HoneypotActionType.SUCCESSFUL_LOGIN);
        }


        return auth;
    }


    private HttpServletRequest getRequest() {
        var requestAttribs = RequestContextHolder.currentRequestAttributes();
        if(requestAttribs instanceof ServletRequestAttributes servletAttribs) {
            return servletAttribs.getRequest();
        } else {
            throw new IllegalStateException();
        }
    }

}
