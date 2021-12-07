package pl.kamilwojcik.passwordkeeper.users.api.dto;

import java.util.UUID;

public record DeleteUserRequest(
        UUID userPubId,
        String username,
        String password
) {
}
