package pl.kamilwojcik.passwordkeeper.users.api.dto;

import java.util.UUID;

public record DeleteUserRequest(
        String username,
        String password
) {
}
