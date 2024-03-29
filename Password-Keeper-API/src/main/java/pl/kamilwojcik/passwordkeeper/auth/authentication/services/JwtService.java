package pl.kamilwojcik.passwordkeeper.auth.authentication.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {

    String createAuthToken(UserDetails userDetails);

    String createRefreshToken(UserDetails userDetails);

    DecodedJWT validateAuthToken(String authToken);

    String refreshAuthToken(String refreshToken);

    String refreshRefreshToken(String refreshToken);

}
