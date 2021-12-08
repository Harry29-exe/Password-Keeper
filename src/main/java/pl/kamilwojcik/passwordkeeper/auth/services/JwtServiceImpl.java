package pl.kamilwojcik.passwordkeeper.auth.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.NotAuthorizedException;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtServiceImpl implements JwtService {
    private final UserDetailsService userDetailsService;
    private final Algorithm authAlgorithm;
    private final Integer authExpInSec;
    private final Algorithm refreshAlgorithm;
    private final Integer refreshExpInSec;

    private static final String TOKEN_TYPE_CLAIM = "Token type";
    private static final String AUTH_TYPE = "Auth";
    private static final String REFRESH_TYPE = "Refresh";

    private Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);


    public JwtServiceImpl(UserDetailsService userDetailsService,
                          @Value("${jwt.secrets.auth}") String authSecret,
                          @Value("${jwt.secrets.refresh}") String refreshSecret,
                          @Value("${jwt.config.auth.exp_in_sec}") Integer authExp,
                          @Value("${jwt.config.refresh.exp_in_sec}") Integer refreshExp) {

        if(authSecret.getBytes(StandardCharsets.UTF_8).length < (256 / 8)) {
            throw new IllegalArgumentException("Auth secret must be at least 256 bit long");
        }
        if(refreshSecret.getBytes(StandardCharsets.UTF_8).length < (512 / 8)) {
            throw new IllegalArgumentException("Refresh secret must be at least 512 bit long");
        }
        this.userDetailsService = userDetailsService;
        this.authAlgorithm = Algorithm.HMAC256(authSecret.getBytes(StandardCharsets.UTF_8));
        this.authExpInSec = authExp;
        this.refreshAlgorithm = Algorithm.HMAC512(refreshSecret.getBytes(StandardCharsets.UTF_8));
        this.refreshExpInSec = refreshExp;
    }

    @Override
    public String createAuthToken(UserDetails userDetails) {
        var now = new Date();
        var exp = new Date(now.getTime() + this.authExpInSec * 1000);

        return JWT.create()
                .withClaim(TOKEN_TYPE_CLAIM, AUTH_TYPE)
                .withSubject(userDetails.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(this.authAlgorithm);
    }

    @Override
    public String createRefreshToken(UserDetails userDetails) {
        var now = new Date();
        var exp = new Date(now.getTime() + this.refreshExpInSec * 1000);

        return JWT.create()
                .withClaim(TOKEN_TYPE_CLAIM, REFRESH_TYPE)
                .withSubject(userDetails.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(refreshAlgorithm);
    }

    @Override
    public DecodedJWT validateAuthToken(@NonNull String authToken) {
        try {
            var token = JWT.decode(authToken);
            JWTVerifier verifier = JWT.require(refreshAlgorithm)
                    .withClaim(TOKEN_TYPE_CLAIM, REFRESH_TYPE)
                    .withSubject(token.getSubject())
                    .build();

            verifier.verify(token);

            return token;
        } catch (JWTDecodeException ex) {
            this.logNonJwtCompatibleTokenValue(authToken, AUTH_TYPE);
            throw new NotAuthorizedException();
        } catch (JWTVerificationException ex) {
            this.logInvalidJwtToken(authToken, ex.getMessage(), AUTH_TYPE);
            throw new NotAuthorizedException();
        }
    }

    @Override
    public String refreshAuthToken(String refreshToken) {
        return null;
    }

    @Override
    public String refreshRefreshToken(String refreshToken) {
        return null;
    }


    private void logNonJwtCompatibleTokenValue(String invalidJWT, String tokenType) {
        var requestAttributes = RequestContextHolder.getRequestAttributes();
        if(requestAttributes == null) {
            logger.error("Client without ip tried pass following string: " +
                    invalidJWT + " as jwt " + tokenType + "token");
            return;
        }
        var request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        var address = request.getRemoteAddr();
        logger.warn("Client with following ip: " + address +
                "tried pass following string: " + invalidJWT +
                " as jwt " + tokenType + "token");
    }

    private void logInvalidJwtToken(String invalidJWT, String rejectReason, String tokenType) {
        var requestAttributes = RequestContextHolder.getRequestAttributes();
        if(requestAttributes == null) {
            logger.error("Client without ip tried to use following invalid jwt token: " + invalidJWT +
                    " as jwt " + tokenType + "token, token was rejected because of the following reason: " +
                    rejectReason);
            return;
        }
        var request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        var address = request.getRemoteAddr();
        logger.warn("Client with following ip: " + address +
                "tried to use following invalid jwt token: " + invalidJWT + " as jwt " + tokenType +
                "token , token was rejected because of the following reason: " + rejectReason);
    }

}
