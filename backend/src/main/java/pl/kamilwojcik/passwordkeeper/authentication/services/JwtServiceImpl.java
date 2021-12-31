package pl.kamilwojcik.passwordkeeper.authentication.services;

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
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.kamilwojcik.passwordkeeper.exceptions.auth.NotAuthorizedException;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    private final Algorithm authAlgorithm;
    private final Integer authExpInSec;
    private final Algorithm refreshAlgorithm;
    private final Integer refreshExpInSec;

    private static final String TOKEN_TYPE_CLAIM = "Token type";
    private static final String AUTH_TYPE = "Auth";
    private static final String REFRESH_TYPE = "Refresh";

    private final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);


    public JwtServiceImpl(@Value("${jwt.secrets.auth}") String authSecret,
                          @Value("${jwt.secrets.refresh}") String refreshSecret,
                          @Value("${jwt.config.auth.exp_in_sec}") Integer authExp,
                          @Value("${jwt.config.refresh.exp_in_sec}") Integer refreshExp) {

        if (authSecret.getBytes(StandardCharsets.UTF_8).length < (256 / 8)) {
            throw new IllegalArgumentException("Auth secret must be at least 256 bit long");
        }
        if (refreshSecret.getBytes(StandardCharsets.UTF_8).length < (512 / 8)) {
            throw new IllegalArgumentException("Refresh secret must be at least 512 bit long");
        }

        this.authAlgorithm = Algorithm.HMAC256(authSecret.getBytes(StandardCharsets.UTF_8));
        this.authExpInSec = authExp;
        this.refreshAlgorithm = Algorithm.HMAC512(refreshSecret.getBytes(StandardCharsets.UTF_8));
        this.refreshExpInSec = refreshExp;
    }

    @Override
    public String createAuthToken(UserDetails userDetails) {
        var username = userDetails.getUsername();
        if (username == null || username.isBlank()) {
            //todo
            throw new IllegalArgumentException();
        }

        return this.createAuthToken(userDetails.getUsername());
    }

    @Override
    public String createRefreshToken(UserDetails userDetails) {
        var username = userDetails.getUsername();
        if (username == null || username.isBlank()) {
            //todo
            throw new IllegalArgumentException();
        }

        return this.createRefreshToken(username);
    }

    @Override
    public DecodedJWT validateAuthToken(@NonNull String authToken) {
        return validateJWT(authToken, authAlgorithm, AUTH_TYPE);
    }

    @Override
    public String refreshAuthToken(String refreshToken) {
        var token = validateRefreshToken(refreshToken);
        var sub = token.getSubject();

        return this.createAuthToken(sub);
    }

    @Override
    public String refreshRefreshToken(String refreshToken) {
        var token = validateRefreshToken(refreshToken);
        var sub = token.getSubject();

        return createRefreshToken(sub);
    }

    private String createAuthToken(@NonNull String subject) {
        var now = new Date();
        var exp = new Date(now.getTime() + this.authExpInSec * 1000);

        return JWT.create()
                .withClaim(TOKEN_TYPE_CLAIM, AUTH_TYPE)
                .withSubject(subject)
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(this.authAlgorithm);
    }

    private String createRefreshToken(@NonNull String subject) {
        var now = new Date();
        var exp = new Date(now.getTime() + this.refreshExpInSec * 1000);

        return JWT.create()
                .withClaim(TOKEN_TYPE_CLAIM, REFRESH_TYPE)
                .withSubject(subject)
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(refreshAlgorithm);
    }

    private DecodedJWT validateRefreshToken(String refreshToken) {
        return validateJWT(refreshToken, refreshAlgorithm, REFRESH_TYPE);
    }

    private DecodedJWT validateJWT(String refreshToken, Algorithm refreshAlgorithm, String refreshType) {
        try {
            var token = JWT.decode(refreshToken);
            var sub = token.getSubject();
            if (sub == null || sub.isBlank()) {
                throw new JWTVerificationException("Subject can not be black or null");
            }
            JWTVerifier verifier = JWT.require(refreshAlgorithm)
                    .withClaim(TOKEN_TYPE_CLAIM, refreshType)
                    .withSubject(sub)
                    .build();

            verifier.verify(token);

            return token;
        } catch (JWTDecodeException ex) {
            this.logNonJwtCompatibleTokenValue(refreshToken, refreshType);
            throw new NotAuthorizedException();
        } catch (JWTVerificationException ex) {
            this.logInvalidJwtToken(refreshToken, ex.getMessage(), refreshType);
            throw new NotAuthorizedException();
        }
    }

    private void logNonJwtCompatibleTokenValue(String invalidJWT, String tokenType) {
        var requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
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
        if (requestAttributes == null) {
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
