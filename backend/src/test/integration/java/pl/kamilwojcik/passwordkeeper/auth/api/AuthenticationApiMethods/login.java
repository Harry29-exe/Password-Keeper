package pl.kamilwojcik.passwordkeeper.auth.api.AuthenticationApiMethods;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.kamilwojcik.passwordkeeper.auth.api.AuthenticationApiTestTemplate;
import pl.kamilwojcik.passwordkeeper.auth.dto.value.LoginRequest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.kamilwojcik.passwordkeeper.auth.utils.DefaultUsersCredentials.USER1_PASSWORD;
import static pl.kamilwojcik.passwordkeeper.auth.utils.DefaultUsersCredentials.USER1_USERNAME;

class login extends AuthenticationApiTestTemplate {

    @Test
    void should_login_default_user() throws Exception {
        var result = getAuthAPIMockMvc()
                .login(
                        new LoginRequest(USER1_USERNAME, USER1_PASSWORD)
                ).andExpect(status().is2xxSuccessful())
                .andReturn();

        var response = result.getResponse();
        var authHeader = response.getHeader("Authorization");
        assert authHeader != null && !authHeader.isBlank();

        var authCookie = response.getCookie("Refresh-Token");
        assert authCookie != null && !authCookie.getValue().isBlank();
        assert authCookie.isHttpOnly() && authCookie.getSecure();
    }

    @Test
    void should_return_401_given_bad_password() throws Exception {
        getAuthAPIMockMvc()
                .login(
                        new LoginRequest(USER1_USERNAME, "123")
                ).andExpect(status().is(403));
    }

    @Test
    void should_return_401_given_non_existing_username() throws Exception {
        getAuthAPIMockMvc()
                .login(
                        new LoginRequest("NonExistingUsername", "123")
                ).andExpect(status().is(403));
    }

}
