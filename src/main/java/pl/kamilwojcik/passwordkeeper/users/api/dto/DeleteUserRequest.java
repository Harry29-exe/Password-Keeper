package pl.kamilwojcik.passwordkeeper.users.api.dto;

public record DeleteUserRequest(
        String userPubId,
        String username,
        String password
) {
}
