package pl.kamilwojcik.passwordkeeper.auth.dto;

import org.springframework.lang.NonNull;

public record AppCredentials(
        @NonNull String username,
        @NonNull String password
) {
}
