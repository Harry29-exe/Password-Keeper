package pl.kamilwojcik.passwordkeeper.auth.services;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pl.kamilwojcik.passwordkeeper.auth.dto.AppAuthentication;
import pl.kamilwojcik.passwordkeeper.users.services.AppUserService;

//@Component
public class AppAuthenticationProvider implements AuthenticationProvider {
    private final AppUserService appUserService;

    public AppAuthenticationProvider(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(! (authentication instanceof AppAuthentication)) {
            //todo(powiniśmy wyzucać 500?)
            throw new IllegalStateException();
        }

        var auth = (AppAuthentication) authentication;
        var user = appUserService.getByUsername(auth.getPrincipal().username());

//        try {
//            assert auth.getPrincipal().publicId().equals(user.getPublicId());
//            assert auth.getPrincipal().username().equals(user.getUsername());
//            assert auth.getCredentials().
//        }


        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AppAuthentication.class.isAssignableFrom(authentication);
    }
}
