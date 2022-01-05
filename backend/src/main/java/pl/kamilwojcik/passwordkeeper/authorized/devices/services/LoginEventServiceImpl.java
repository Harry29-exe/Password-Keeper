package pl.kamilwojcik.passwordkeeper.authorized.devices.services;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities.LoginEvent;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.repositories.AuthorizedDeviceRepository;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.repositories.LoginEventRepository;
import pl.kamilwojcik.passwordkeeper.authorized.devices.dto.LoggingEventDTO;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.dto.CreateLoginEvent;
import pl.kamilwojcik.passwordkeeper.exceptions.resource.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional
public class LoginEventServiceImpl implements LoggingEventService {
    private final AuthorizedDeviceRepository authorizedDeviceRepo;
    private final LoginEventRepository loginEventRepo;

    public LoginEventServiceImpl(
            AuthorizedDeviceRepository authorizedDeviceRepo,
            LoginEventRepository loginEventRepo) {
        this.authorizedDeviceRepo = authorizedDeviceRepo;
        this.loginEventRepo = loginEventRepo;
    }

    @Override
    public void addNewLoggingEvent(CreateLoginEvent loginEvent) {
        var device = authorizedDeviceRepo
                .findByPublicIdAndUser_Username(
                        loginEvent.getDevicePublicId(),
                        loginEvent.getUsername())
                .orElseThrow(ResourceNotFoundException::new);


        var entity = new LoginEvent(
                loginEvent.getLoginDate(),
                device
        );

        loginEventRepo.save(entity);
    }

    @Override
    public Integer countAllUserLoggingEvents(String username) {
//        return loginEventRepo.count;
        //todo
        return null;
    }

    @Override
    public List<LoggingEventDTO> getLoggingEvents(String username, Pageable pageable) {
        var loginEvents = loginEventRepo.findAllByDevice_User_UsernameOrderByLoginDate(
                username, pageable
        );

        return loginEvents.stream()
                .map(loginEvent -> new LoggingEventDTO(
                        loginEvent.getPublicId(),
                        loginEvent.getLoginDate(),
                        loginEvent.getDevice().getPublicId()
                )).toList();
    }
}
