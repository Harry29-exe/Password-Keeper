package pl.kamilwojcik.passwordkeeper.users.services.dto;

public record CreateUser(
        String username,
        String email,
        String nonEncodedPassword,
        String storageNonEncodedPassword
) {
}
