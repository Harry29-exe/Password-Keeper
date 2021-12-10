package pl.kamilwojcik.passwordkeeper.passwords_storage.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kamilwojcik.passwordkeeper.passwords_storage.domain.entities.FuturePasswordEntity;

@Repository
public interface FuturePasswordRepository extends JpaRepository<FuturePasswordEntity, Long> {

}
