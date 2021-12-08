package pl.kamilwojcik.passwordkeeper.users.services;

import pl.kamilwojcik.passwordkeeper.auth.dto.AppUser;

import java.util.UUID;

public interface AppUserService {

    AppUser getByPublicId(UUID publicId);

    AppUser getByUsername(String username);

}
