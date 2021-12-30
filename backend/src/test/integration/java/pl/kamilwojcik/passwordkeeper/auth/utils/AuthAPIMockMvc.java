package pl.kamilwojcik.passwordkeeper.auth.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import pl.kamilwojcik.passwordkeeper.auth.dto.value.LoginRequest;

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

}
