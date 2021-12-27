package pl.kamilwojcik.passwordkeeper.passwords_storage.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordInfoDto;

import java.util.List;

@Repository
public interface PasswordRepository extends JpaRepository<PasswordEntity, Long> {

    List<PasswordInfoDto> findDtoByUser_Username(String username);

    List<PasswordEntity> findAllByUser_Username(String username);

    PasswordEntity findByUser_UsernameAndPasswordName(String username, String passwordName);

    boolean existsByUser_UsernameAndPasswordName(String username, String passwordName);

    void deleteByUser_UsernameAndPasswordName(String username, String passwordName);


}
