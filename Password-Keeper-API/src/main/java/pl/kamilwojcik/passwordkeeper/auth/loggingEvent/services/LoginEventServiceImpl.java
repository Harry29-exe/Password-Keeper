package pl.kamilwojcik.passwordkeeper.auth.loggingEvent.services;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kamilwojcik.passwordkeeper.auth.authorized_devices.domain.entities.ClientDevice;
import pl.kamilwojcik.passwordkeeper.auth.authorized_devices.domain.repositories.ClientDeviceRepository;
import pl.kamilwojcik.passwordkeeper.auth.authorized_devices.services.dto.CreateLoginEvent;
import pl.kamilwojcik.passwordkeeper.auth.loggingEvent.domain.LoginEvent;
import pl.kamilwojcik.passwordkeeper.auth.loggingEvent.domain.LoginEventRepository;
import pl.kamilwojcik.passwordkeeper.auth.loggingEvent.dto.LoginEventDTO;
import pl.kamilwojcik.passwordkeeper.exceptions.resource.IllegalNoResourceException;
import pl.kamilwojcik.passwordkeeper.users.domain.repositories.UserRepository;

import java.util.List;

@Service
@Transactional
public class LoginEventServiceImpl implements LoginEventService {
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
    public void addNewLoggingEvent(CreateLoginEvent newEvent) {
        var user = userRepo.findByUsername(newEvent.getUsername())
                .orElseThrow(IllegalNoResourceException::new);

        LoginEvent entity;
        if (newEvent.getDevicePublicId() != null) {

            entity = new LoginEvent(
                    newEvent.getLoginDate(),
                    newEvent.getResult(),
                    getClientDevice(newEvent),
                    newEvent.getIpAddress(),
                    user
            );
        } else {
            entity = new LoginEvent(
                    newEvent.getLoginDate(),
                    newEvent.getResult(),
                    newEvent.getUserAgent(),
                    newEvent.getIpAddress(),
                    user
            );
        }

        loginEventRepo.saveAndFlush(entity);
    }

    @Override
    public Integer countAllUserLoggingEvents(String username) {
        return loginEventRepo.countAllByUser_Username(username);
    }

    @Override
    public List<LoginEventDTO> getLoggingEvents(String username, Pageable pageable) {
        var loginEvents = loginEventRepo.findAllByUser_UsernameOrderByLoginDate(
                username, pageable
        );

        return loginEvents.stream()
                .map(this::mapLoginEvent)
                .toList();
    }

    private LoginEventDTO mapLoginEvent(LoginEvent loginEvent) {
        var device = loginEvent.getDevice();
        var userAgent = device == null ?
                loginEvent.getUserAgent()
                : device.getUserAgent();
        var devicePubId = device == null ? null : device.getPublicId();


        return new LoginEventDTO(
                loginEvent.getPublicId(),
                loginEvent.getLoginDate(),
                loginEvent.getIpAddress(),
                userAgent,
                devicePubId
        );
    }

    private ClientDevice getClientDevice(CreateLoginEvent loginEvent) {
        return authorizedDeviceRepo
                .findByPublicIdAndUser_Username(
                        loginEvent.getDevicePublicId(),
                        loginEvent.getUsername())
                .orElseThrow(IllegalNoResourceException::new);
    }

}
