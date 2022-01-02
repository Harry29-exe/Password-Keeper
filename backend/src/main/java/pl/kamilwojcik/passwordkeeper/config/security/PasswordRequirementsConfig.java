package pl.kamilwojcik.passwordkeeper.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kamilwojcik.passwordkeeper.validation.password.specyfication.PasswordSpec;

import java.util.List;

@Configuration
public class PasswordRequirementsConfig {

    @Bean
    public PasswordSpec getPasswordRequirements() {
        return new PasswordSpec(List.of('!','#','$','%','&','(',')','*','+',',','-','.','/',':',';','@','[',']','^','_','~'),
                12, 3, 1, 2, false,1);
    }

}
