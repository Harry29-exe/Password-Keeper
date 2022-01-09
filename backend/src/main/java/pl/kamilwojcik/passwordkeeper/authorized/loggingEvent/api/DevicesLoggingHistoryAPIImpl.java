package pl.kamilwojcik.passwordkeeper.authorized.loggingEvent.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import pl.kamilwojcik.passwordkeeper.authorized.devices.api.dto.LoggingHistoryResponse;
import pl.kamilwojcik.passwordkeeper.authorized.loggingEvent.services.LoginEventService;

@RestController
public class DevicesLoggingHistoryAPIImpl implements DevicesLoggingHistoryAPI {
    private final LoginEventService loginEventService;

    public DevicesLoggingHistoryAPIImpl(LoginEventService loginEventService) {
        this.loginEventService = loginEventService;
    }

    @Override
    public LoggingHistoryResponse getLoggingHistory(
            Integer page,
            Integer itemsPerPage,
            Authentication auth) {
        var events = loginEventService.getLoggingEvents(
                auth.getName(), PageRequest.of(page, itemsPerPage));

        var eventsCount = loginEventService.countAllUserLoggingEvents(
                auth.getName()
        );

        return new LoggingHistoryResponse(
                page,
                (int) Math.floor(((double) eventsCount) / itemsPerPage),
                itemsPerPage,
                events
        );
    }

}
