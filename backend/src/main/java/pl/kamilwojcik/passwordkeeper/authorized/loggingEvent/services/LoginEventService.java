package pl.kamilwojcik.passwordkeeper.authorized.loggingEvent.services;

import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.dto.CreateLoginEvent;
import pl.kamilwojcik.passwordkeeper.authorized.loggingEvent.dto.LoggingEventDTO;

import java.util.List;

public interface LoginEventService {

    @PreAuthorize("@authFunctions.usernamesMatch(#loginEvent.username)")
    void addNewLoggingEvent(CreateLoginEvent loginEvent);

    @PreAuthorize("@authFunctions.usernamesMatch(#username)")
    Integer countAllUserLoggingEvents(String username);

    @PreAuthorize("@authFunctions.usernamesMatch(#username)")
    List<LoggingEventDTO> getLoggingEvents(String username, Pageable pageable);


}
