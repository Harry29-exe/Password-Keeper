package pl.kamilwojcik.passwordkeeper.auth.authorized_devices.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kamilwojcik.passwordkeeper.auth.authorized_devices.domain.entities.ClientDevice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientDeviceRepository extends JpaRepository<ClientDevice, Long> {

    List<ClientDevice> findByUser_Username(String username);

    Optional<ClientDevice> findByPublicIdAndUser_Username(UUID publicId, String username);

    Optional<ClientDevice> findByIpAddressAndUserAgentAndUser_Username(String ipAddress, String userAgent, String username);

    boolean existsByIpAddressAndUserAgentAndUser_UsernameAndIsAuthorizedIsTrue(
            String ipAddress,
            String userAgent,
            String username);

    boolean existsByPublicIdAndUser_Username(UUID publicId, String username);

    void deleteByPublicId(UUID publicId);

}
