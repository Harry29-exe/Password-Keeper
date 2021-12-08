package pl.kamilwojcik.passwordkeeper.auth.services;

import pl.kamilwojcik.passwordkeeper.auth.dto.AppAuthentication;
import pl.kamilwojcik.passwordkeeper.auth.dto.AppCredentials;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.NotAuthorizedException;
import pl.kamilwojcik.passwordkeeper.users.dao.repositories.UserRepository;

import java.util.UUID;

public class JwtAuthenticationService implements JwtService {
    private final UserRepository userRepo;

    public JwtAuthenticationService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public String createAuthToken(UUID userPubId) {
        return null;
    }

    @Override
    public String createRefreshToken(UUID userPubId) {
        return null;
    }

    @Override
    public AppAuthentication validateAndAuthenticate(String authToken) {
        return null;
    }

    @Override
    public String refreshAuthToken(String refreshToken) {
        return null;
    }

    @Override
    public String refreshRefreshToken(String refreshToken) {
        return null;
    }
}
