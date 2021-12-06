package pl.kamilwojcik.passwordkeeper.auth.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface JwtService {

    String createAuthToken(UUID userPubId);

    String createRefreshToken(UUID userPubId);

    void validateAuthToken(String authToken);

    String refreshAuthToken(String refreshToken);

}
