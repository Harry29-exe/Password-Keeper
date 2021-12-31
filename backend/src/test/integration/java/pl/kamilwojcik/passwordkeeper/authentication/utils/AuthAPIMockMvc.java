package pl.kamilwojcik.passwordkeeper.authentication.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import pl.kamilwojcik.passwordkeeper.authentication.dto.value.LoginRequest;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class AuthAPIMockMvc {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AuthAPIMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public ResultActions login(LoginRequest requestBody) throws Exception {
        return mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody))
        );
    }

    public ResultActions refreshAuthToken(String refreshToken) throws Exception {
        var cookie = new Cookie("Refresh-Token", refreshToken);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);

        return mockMvc.perform(
                post("/refresh/auth-token")
                        .cookie(cookie)
        );
    }

}
