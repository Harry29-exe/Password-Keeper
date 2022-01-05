package pl.kamilwojcik.passwordkeeper.authorized.devices.services;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities.LoginEvent;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.repositories.ClientDeviceRepository;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.repositories.LoginEventRepository;
import pl.kamilwojcik.passwordkeeper.authorized.devices.dto.LoggingEventDTO;
import pl.kamilwojcik.passwordkeeper.authorized.devices.services.dto.CreateLoginEvent;
import pl.kamilwojcik.passwordkeeper.exceptions.resource.IllegalNoResourceException;
import pl.kamilwojcik.passwordkeeper.users.domain.repositories.UserRepository;

import java.util.List;

@Service
@Transactional
public class LoginEventServiceImpl implements LoggingEventService {
    private final UserRepository userRepo;
    private final ClientDeviceRepository authorizedDeviceRepo;
    private final LoginEventRepository loginEventRepo;

    public LoginEventServiceImpl(
            UserRepository userRepo, ClientDeviceRepository authorizedDeviceRepo,
            LoginEventRepository loginEventRepo) {
        this.userRepo = userRepo;
        this.authorizedDeviceRepo = authorizedDeviceRepo;
        this.loginEventRepo = loginEventRepo;
    }

    @Override
    public void addNewLoggingEvent(CreateLoginEvent loginEvent) {
        var user = userRepo.findByUsername(loginEvent.getUsername())
                .orElseThrow(IllegalNoResourceException::new);

        var device = authorizedDeviceRepo
                .findByPublicIdAndUser_Username(
                        loginEvent.getDevicePublicId(),
                        loginEvent.getUsername())
                .orElseThrow(IllegalNoResourceException::new);


        var entity = new LoginEvent(
                loginEvent.getLoginDate(),
                loginEvent.getResult(),
                device,
                user
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
