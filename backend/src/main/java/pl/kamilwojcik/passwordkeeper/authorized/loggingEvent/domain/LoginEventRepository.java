package pl.kamilwojcik.passwordkeeper.authorized.loggingEvent.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginEventRepository extends JpaRepository<LoginEvent, Long> {

    List<LoginEvent> findAllByUser_UsernameOrderByLoginDate(String username, Pageable pageable);

    Integer countAllByUser_Username(String username);


}
