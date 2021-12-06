package pl.kamilwojcik.passwordkeeper.users.dto.values;

public record CreateUser(
        String username,
        String nonEncodedPassword,
        String storageNonEncodedPassword
) {
}
