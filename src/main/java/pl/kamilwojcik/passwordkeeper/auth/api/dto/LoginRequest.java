package pl.kamilwojcik.passwordkeeper.auth.api.dto;

public record LoginRequest(
        String username,
        String password
) {
}
