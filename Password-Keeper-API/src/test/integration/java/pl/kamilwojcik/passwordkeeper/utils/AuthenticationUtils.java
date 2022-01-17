package pl.kamilwojcik.passwordkeeper.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.web.servlet.MockMvc;

@Profile("test")
public class AuthenticationUtils {

    @Autowired
    MockMvc mockMvc;

    public String getAuthenticationHeader() {
        //TODO-TEST
        return "";
    }

}
