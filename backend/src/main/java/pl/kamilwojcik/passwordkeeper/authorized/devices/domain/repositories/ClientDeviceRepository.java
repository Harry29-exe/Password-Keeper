package pl.kamilwojcik.passwordkeeper.authorized.devices.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities.ClientDevice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientDeviceRepository extends JpaRepository<ClientDevice, Long> {

    List<ClientDevice> findByUser_Username(String username);

    Optional<ClientDevice> findByPublicIdAndUser_Username(UUID publicId, String username);

    boolean existsByIpAddressAndUserAgentAndUser_Username(
            String ipAddress,
            String userAgent,
            String username);

    boolean existsByPublicIdAndUser_Username(UUID publicId, String username);

    void deleteByPublicId(UUID publicId);

}
