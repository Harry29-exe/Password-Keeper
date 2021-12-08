package pl.kamilwojcik.passwordkeeper.auth.dto;

import java.util.UUID;

public class BasicAppUser implements AppUser {
    private final UUID publicId;
    private final String username;
    private final String password;

    public BasicAppUser(UUID publicId, String username, String password) {
        this.publicId = publicId;
        this.username = username;
        this.password = password;
    }

    @Override
    public UUID getPublicId() {
        return publicId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
