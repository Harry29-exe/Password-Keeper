package pl.kamilwojcik.passwordkeeper.auth.loggingEvent.services;

import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import pl.kamilwojcik.passwordkeeper.auth.authorized_devices.services.dto.CreateLoginEvent;
import pl.kamilwojcik.passwordkeeper.auth.loggingEvent.dto.LoginEventDTO;

import java.util.List;

public interface LoginEventService {

    void addNewLoggingEvent(CreateLoginEvent loginEvent);

    @PreAuthorize("@authFunctions.usernamesMatch(#username)")
    Integer countAllUserLoggingEvents(String username);

    @PreAuthorize("@authFunctions.usernamesMatch(#username)")
    List<LoginEventDTO> getLoggingEvents(String username, Pageable pageable);


}
