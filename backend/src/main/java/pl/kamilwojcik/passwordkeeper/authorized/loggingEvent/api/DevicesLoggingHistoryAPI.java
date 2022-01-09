package pl.kamilwojcik.passwordkeeper.authorized.loggingEvent.api;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kamilwojcik.passwordkeeper.authorized.devices.api.dto.LoggingHistoryResponse;

@RequestMapping("logging-history")
public interface DevicesLoggingHistoryAPI {

    @GetMapping
    LoggingHistoryResponse getLoggingHistory(
            @RequestParam Integer page,
            @RequestParam Integer itemsPerPage,
            Authentication auth
    );

}