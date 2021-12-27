package pl.kamilwojcik.passwordkeeper.users.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kamilwojcik.passwordkeeper.users.domain.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var entity = userRepository.findByUsername(username)
                .orElseThrow(EntityNotFoundException::new);

        return new User(
                entity.getUsername(),
                entity.getPassword(),
                //todo
                new ArrayList<>()
        );
    }
}
