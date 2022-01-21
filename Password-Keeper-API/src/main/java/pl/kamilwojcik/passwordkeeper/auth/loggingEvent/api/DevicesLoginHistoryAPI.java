package pl.kamilwojcik.passwordkeeper.auth.loggingEvent.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kamilwojcik.passwordkeeper.auth.authorized_devices.api.dto.LoginHistoryResponse;
import pl.kamilwojcik.passwordkeeper.config.DefaultCors;

@DefaultCors
@RequestMapping("login-history")
@PreAuthorize("isAuthenticated()")
public interface DevicesLoginHistoryAPI {

    @GetMapping
    LoginHistoryResponse getLoggingHistory(
            @RequestParam Integer page,
            @RequestParam Integer itemsPerPage,
            Authentication auth
    );

}
