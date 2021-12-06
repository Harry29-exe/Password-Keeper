package pl.kamilwojcik.passwordkeeper.users.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kamilwojcik.passwordkeeper.users.dao.entities.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByPublicId(UUID publicId);

}
