package pl.kamilwojcik.passwordkeeper.authorized.devices.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities.AuthorizedDevice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorizedDeviceRepository extends JpaRepository<AuthorizedDevice, Long> {

    List<AuthorizedDevice> findByUser_Username(String username);

    Optional<AuthorizedDevice> findByPublicIdAndUser_Username(UUID publicId, String username);

    boolean existsByIpAddressAndUserAgentAndUser_Username(
            String ipAddress,
            String userAgent,
            String username);

    boolean existsByPublicIdAndUser_Username(UUID publicId, String username);

    void deleteByPublicId(UUID publicId);

}
