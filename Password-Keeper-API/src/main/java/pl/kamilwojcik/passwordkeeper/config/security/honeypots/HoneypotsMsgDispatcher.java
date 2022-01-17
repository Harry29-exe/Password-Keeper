package pl.kamilwojcik.passwordkeeper.config.security.honeypots;

import org.springframework.security.core.Authentication;

public interface HoneypotsMsgDispatcher {

    void dispatchMsg(Authentication honeypotAccount, HoneypotActionType actionType);

}
