package pl.kamilwojcik.passwordkeeper.config.security.honeypots;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.kamilwojcik.passwordkeeper.utils.CurrentRequestUtils;

import java.util.logging.Logger;

@Component
public class HoneypotsMsgLogger implements HoneypotsMsgDispatcher {
    private final Logger logger = Logger.getLogger(HoneypotsMsgDispatcher.class.getName());

    @Override
    public void dispatchMsg(Authentication honeypotAccount, HoneypotActionType actionType) {
        var request = CurrentRequestUtils.getCurrentRequest();
        if (actionType == HoneypotActionType.ACCESSING_ENDPOINT) {
            logger.warning("Honeypot account with username: " + honeypotAccount.getName() +
                    ", is trying to access " + request.getRequestURI() +
                    " from ip: " + CurrentRequestUtils.getPreProxyIp()
            );

        } else if (actionType == HoneypotActionType.LOGIN_ATTEMPT) {
            logger.warning("Person with ip: " + CurrentRequestUtils.getPreProxyIp() +
                    " made attempt to login on honeypot account with following username" + honeypotAccount.getName()
            );

        } else if (actionType == HoneypotActionType.SUCCESSFUL_LOGIN) {
            logger.warning("Person with ip: " + CurrentRequestUtils.getPreProxyIp() +
                    " made SUCCESSFUL attempt to login on honeypot account with following username" + honeypotAccount.getName()
            );


        }
    }

}
