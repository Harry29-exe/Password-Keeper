package pl.kamilwojcik.passwordkeeper.passwords_storage.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import pl.kamilwojcik.passwordkeeper.utils.ExecuteDML;

import static org.junit.jupiter.api.Assertions.*;

@ExecuteDML
@SpringBootTest
@AutoConfigureMockMvc
public class PasswordApiControllerTemplate {

    @Autowired
    protected MockMvc mockMvc;


}