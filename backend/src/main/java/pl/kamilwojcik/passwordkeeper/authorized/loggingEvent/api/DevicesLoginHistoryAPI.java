package pl.kamilwojcik.passwordkeeper.authorized.loggingEvent.api;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kamilwojcik.passwordkeeper.authorized.devices.api.dto.LoginHistoryResponse;

import static pl.kamilwojcik.passwordkeeper.config.consts.CorsAddresses.FRONTEND_ADDRESS;

@RequestMapping("login-history")
@CrossOrigin(origins = FRONTEND_ADDRESS)
public interface DevicesLoginHistoryAPI {

    @GetMapping
    LoginHistoryResponse getLoggingHistory(
            @RequestParam Integer page,
            @RequestParam Integer itemsPerPage,
            Authentication auth
    );

}
