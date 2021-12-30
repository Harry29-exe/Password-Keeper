package pl.kamilwojcik.passwordkeeper.auth.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import pl.kamilwojcik.passwordkeeper.auth.utils.AuthAPIMockMvc;

import static org.junit.jupiter.api.Assertions.*;

@Sql(scripts = {"/sql/drop.sql", "/sql/add_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
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