package pl.kamilwojcik.passwordkeeper.auth.dto.value;

public record LoginRequest(
        String username,
        String password
) {
}
