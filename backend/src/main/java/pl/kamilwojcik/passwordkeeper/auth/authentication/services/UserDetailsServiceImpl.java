package pl.kamilwojcik.passwordkeeper.auth.authentication.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kamilwojcik.passwordkeeper.auth.account_lock.domain.AccountLockRepository;
import pl.kamilwojcik.passwordkeeper.users.domain.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final AccountLockRepository accountLockRepository;


    public UserDetailsServiceImpl(UserRepository userRepository, AccountLockRepository accountLockRepository) {
        this.userRepository = userRepository;
        this.accountLockRepository = accountLockRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var entity = userRepository.findByUsername(username)
                .orElseThrow(EntityNotFoundException::new);


        var isNonLocked = accountLockRepository
                .findByUser_UsernameAndLockedToAfter(
                        entity.getUsername(),
                        new Date()
                ).isEmpty();

        return new User(
                entity.getUsername(),
                entity.getPassword(),
                true,
                true,
                true,
                isNonLocked,
                //todo
                new ArrayList<>()
        );
    }
}
