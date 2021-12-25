package pl.kamilwojcik.passwordkeeper.users.api.dto;

public record DeleteUserRequest(
        String username,
        String password
) {
}
