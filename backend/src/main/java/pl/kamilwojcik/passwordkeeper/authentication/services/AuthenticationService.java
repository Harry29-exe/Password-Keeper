package pl.kamilwojcik.passwordkeeper.authentication.services;

import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AuthenticationService {

    Authentication authenticate(String username, String password);

}
