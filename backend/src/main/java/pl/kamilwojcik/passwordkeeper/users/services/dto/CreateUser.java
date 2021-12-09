package pl.kamilwojcik.passwordkeeper.users.services.dto;

public record CreateUser(
        String username,
        String nonEncodedPassword,
        String storageNonEncodedPassword
) {
}
