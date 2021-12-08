package pl.kamilwojcik.passwordkeeper.auth.dto;

import java.util.UUID;

public interface AppUser {

    UUID getPublicId();

    String getUsername();

    String getPassword();



}
