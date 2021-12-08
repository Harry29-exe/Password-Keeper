package pl.kamilwojcik.passwordkeeper.auth.dto;

import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class AppAuthentication implements Authentication {
    private final @NonNull AppPrincipal principal;
    private final @NonNull AppCredentials credentials;
    private final @NonNull Collection<? extends GrantedAuthority> authorities;
    private Boolean authenticated = false;

    public AppAuthentication(@NonNull UUID publicId,
                             @NonNull String username,
                             @NonNull String password,
                             @NonNull Collection<? extends GrantedAuthority> authorities) {
        this.principal = new AppPrincipal(publicId, username);
        this.credentials = new AppCredentials(username, password);
        this.authorities = authorities;
    }

    public AppAuthentication(@NonNull UUID publicId,
                             @NonNull String username,
                             @NonNull String password) {
        this(publicId, username, password, List.of());
    }

    @NonNull
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @NonNull
    @Override
    public AppCredentials getCredentials() {
        return credentials;
    }

    @NonNull
    @Override
    public AppPrincipal getDetails() {
        return principal;
    }

    @NonNull
    @Override
    public AppPrincipal getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @NonNull
    @Override
    public String getName() {
        return principal.username();
    }
}
