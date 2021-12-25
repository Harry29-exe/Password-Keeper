package pl.kamilwojcik.passwordkeeper.passwords_storage.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordRepository extends JpaRepository<PasswordEntity, Long> {

    List<PasswordEntity> findAllByUser_Username(String username);


}
