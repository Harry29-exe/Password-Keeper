package pl.kamilwojcik.passwordkeeper.auth.api.AuthenticationApiControllerTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.kamilwojcik.passwordkeeper.auth.api.AuthenticationApiTestTemplate;
import pl.kamilwojcik.passwordkeeper.auth.dto.value.LoginRequest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class login extends AuthenticationApiTestTemplate {

    @BeforeAll
    static void setUp() {

    }

    @Test
    void should_login_default_user() throws Exception {
        var result = getAuthAPIMockMvc().login(
                new LoginRequest("bob", "Strong_P@ssword123")
                ).andExpect(status().is2xxSuccessful())
                .andReturn();

        var response = result.getResponse();
        var authHeader = response.getHeader("Authorization");
        assert authHeader != null && !authHeader.isBlank();

        var authCookie = response.getCookie("Refresh-Token");
        assert authCookie != null && !authCookie.getValue().isBlank();
        assert authCookie.isHttpOnly() && authCookie.getSecure();
    }

//    @Test
//    void

}
