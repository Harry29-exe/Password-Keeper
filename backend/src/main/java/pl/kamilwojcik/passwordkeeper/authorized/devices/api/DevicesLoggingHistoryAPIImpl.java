package pl.kamilwojcik.passwordkeeper.authorized.devices.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import pl.kamilwojcik.passwordkeeper.authorized.devices.api.dto.LoggingHistoryPageInfo;
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

        //todo
        return null;
    }

    @Override
    public LoggingHistoryPageInfo getLoggingHistoryPageInfo(Integer itemsPerPage, Authentication auth) {
        return null;
    }

}
