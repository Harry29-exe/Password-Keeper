package pl.kamilwojcik.passwordkeeper.auth.authentication.services;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {

    Authentication authenticate(String username, String password);

}
