package pl.kamilwojcik.passwordkeeper.authorized.devices.domain.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kamilwojcik.passwordkeeper.authorized.devices.domain.entities.LoginEvent;

import java.util.List;

@Repository
public interface LoginEventRepository extends JpaRepository<LoginEvent, Long> {

    List<LoginEvent> findAllByDevice_User_UsernameOrderByLoginDate(String username, Pageable pageable);

    Integer countAllBy(String username);


}
