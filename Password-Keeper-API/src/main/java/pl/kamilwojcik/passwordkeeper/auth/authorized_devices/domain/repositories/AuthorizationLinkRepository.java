package pl.kamilwojcik.passwordkeeper.auth.authorized_devices.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kamilwojcik.passwordkeeper.auth.authorized_devices.domain.entities.DeviceAuthorizationLink;

import java.util.Optional;
import java.util.UUID;

public interface AuthorizationLinkRepository extends JpaRepository<DeviceAuthorizationLink, Long> {

    Optional<DeviceAuthorizationLink> findByAuthorizationLink(UUID authorizationLink);

}
