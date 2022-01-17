package pl.kamilwojcik.passwordkeeper.auth.loggingEvent.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LoginEventRepository extends JpaRepository<LoginEvent, Long> {

    List<LoginEvent> findAllByUser_UsernameOrderByLoginDate(String username, Pageable pageable);

    List<LoginEvent> findAllByUser_UsernameAndLoginDateIsAfter(String username, Date afterDate);

    Integer countAllByUser_Username(String username);


}
