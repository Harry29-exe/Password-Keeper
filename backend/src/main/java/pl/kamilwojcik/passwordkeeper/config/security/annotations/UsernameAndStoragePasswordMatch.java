package pl.kamilwojcik.passwordkeeper.config.security.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("@authFunctions.usernamesMatch(#username) and @authFunctions.storagePasswordMatch(#storagePassword, #username)")
public @interface UsernameAndStoragePasswordMatch {
}
