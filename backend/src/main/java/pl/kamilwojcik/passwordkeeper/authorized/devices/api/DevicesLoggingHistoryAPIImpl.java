package pl.kamilwojcik.passwordkeeper.authorized.devices.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import pl.kamilwojcik.passwordkeeper.authorized.devices.api.dto.LoggingHistoryResponse;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.LoggingEventService;

@RestController
public class DevicesLoggingHistoryAPIImpl implements DevicesLoggingHistoryAPI {
    private final LoggingEventService loggingEventService;

    public DevicesLoggingHistoryAPIImpl(LoggingEventService loggingEventService) {
        this.loggingEventService = loggingEventService;
    }

    @Override
    public LoggingHistoryResponse getLoggingHistory(
            Integer page,
            Integer itemsPerPage,
            Authentication auth) {
        var events = loggingEventService.getLoggingEvents(
                auth.getName(), PageRequest.of(page, itemsPerPage));

        var eventsCount = loggingEventService.countAllUserLoggingEvents(
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
