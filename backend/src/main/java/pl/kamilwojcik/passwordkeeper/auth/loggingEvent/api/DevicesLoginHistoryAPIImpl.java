package pl.kamilwojcik.passwordkeeper.auth.loggingEvent.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import pl.kamilwojcik.passwordkeeper.auth.authorized_devices.api.dto.LoginHistoryResponse;
import pl.kamilwojcik.passwordkeeper.auth.loggingEvent.services.LoginEventService;

@RestController
public class DevicesLoginHistoryAPIImpl implements DevicesLoginHistoryAPI {
    private final LoginEventService loginEventService;

    public DevicesLoginHistoryAPIImpl(LoginEventService loginEventService) {

        this.loginEventService = loginEventService;
    }

    @Override
    public LoginHistoryResponse getLoggingHistory(
            Integer page,
            Integer itemsPerPage,
            Authentication auth) {
        var events = loginEventService.getLoggingEvents(
                auth.getName(), PageRequest.of(page - 1, itemsPerPage));

        var eventsCount = loginEventService.countAllUserLoggingEvents(
                auth.getName()
        );

        return new LoginHistoryResponse(
                page,
                (int) Math.floor(((double) eventsCount) / itemsPerPage),
                itemsPerPage,
                events
        );
    }

}
