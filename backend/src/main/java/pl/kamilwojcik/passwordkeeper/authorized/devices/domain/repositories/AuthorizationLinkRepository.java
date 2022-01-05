package pl.kamilwojcik.passwordkeeper.authorized.devices.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities.DeviceAuthorizationLink;

import java.util.Optional;
import java.util.UUID;

public interface AuthorizationLinkRepository extends JpaRepository<DeviceAuthorizationLink, Long> {

    Optional<DeviceAuthorizationLink> findByAuthorizationLink(UUID authorizationLink);

}
