package pl.kamilwojcik.passwordkeeper.auth.dto;

import org.springframework.lang.NonNull;

import java.util.UUID;

public record AppPrincipal(
        @NonNull UUID publicId,
        @NonNull String username
) {
}
