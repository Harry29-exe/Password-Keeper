package pl.kamilwojcik.passwordkeeper.config.security.honeypots;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Component
public class HoneypotsMsgLogger implements HoneypotsMsgDispatcher {
    private final Logger logger = Logger.getLogger(HoneypotsMsgDispatcher.class.getName());

    @Override
    public void dispatchMsg(Authentication honeypotAccount, HoneypotActionType actionType) {
        var request = getRequest();
        if(actionType == HoneypotActionType.ACCESSING_ENDPOINT) {
            logger.warning("Honeypot account with username: " + honeypotAccount.getName() +
                    ", is trying to access " + request.getRequestURI() +
                    " from ip: " + request.getRemoteAddr()
            );

        } else if(actionType == HoneypotActionType.LOGIN_ATTEMPT) {
            logger.warning("Person with ip: " + request.getRemoteAddr() +
                            " made attempt to login on honeypot account with following username" + honeypotAccount.getName()
            );

        } else if(actionType == HoneypotActionType.SUCCESSFUL_LOGIN) {
            logger.warning("Person with ip: " + request.getRemoteAddr() +
                    " made SUCCESSFUL attempt to login on honeypot account with following username" + honeypotAccount.getName()
            );


        }
    }

    private HttpServletRequest getRequest() {
        if(RequestContextHolder.getRequestAttributes() == null) {
            throw new IllegalStateException();
        }

        var attribs = RequestContextHolder.getRequestAttributes();
        if(!(attribs instanceof ServletRequestAttributes)) {
            throw new IllegalStateException();
        }

        return ((ServletRequestAttributes) attribs).getRequest();
    }

}
