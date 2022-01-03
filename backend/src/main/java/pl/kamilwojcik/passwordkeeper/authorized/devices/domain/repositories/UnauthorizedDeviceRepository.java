package pl.kamilwojcik.passwordkeeper.authorized.devices.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities.UnauthorizedDevice;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UnauthorizedDeviceRepository extends
        JpaRepository<UnauthorizedDevice, Long> {

    Optional<UnauthorizedDevice> findByAuthorizationLink(String authorizationLink);

}
