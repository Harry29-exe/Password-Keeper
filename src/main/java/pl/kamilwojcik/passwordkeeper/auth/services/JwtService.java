package pl.kamilwojcik.passwordkeeper.auth.services;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.kamilwojcik.passwordkeeper.auth.dto.AppAuthentication;

import java.util.UUID;

@Service
public interface JwtService {

    String createAuthToken(UUID userPubId);

    String createRefreshToken(UUID userPubId);

    AppAuthentication validateAndAuthenticate(String authToken);

    String refreshAuthToken(String refreshToken);

    String refreshRefreshToken(String refreshToken);

}
