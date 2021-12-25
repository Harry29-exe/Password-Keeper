package pl.kamilwojcik.passwordkeeper.users.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kamilwojcik.passwordkeeper.users.domain.entities.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByPublicId(UUID publicId);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> getByUsername(String username);

    Boolean existsByPublicId(UUID publicId);

    Boolean existsByUsername(String username);

    void deleteByPublicId(UUID publicId);

    void deleteByUsername(String username);

}
