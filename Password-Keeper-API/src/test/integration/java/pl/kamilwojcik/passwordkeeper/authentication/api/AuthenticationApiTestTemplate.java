package pl.kamilwojcik.passwordkeeper.authentication.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import pl.kamilwojcik.passwordkeeper.authentication.utils.AuthAPIMockMvc;
import pl.kamilwojcik.passwordkeeper.utils.ExecuteDML;

@ExecuteDML
@ActiveProfiles("tests")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthenticationApiTestTemplate {

    @Autowired
    protected MockMvc mockMvc;

    protected AuthAPIMockMvc getAuthAPIMockMvc() {
        return new AuthAPIMockMvc(mockMvc);
    }

}