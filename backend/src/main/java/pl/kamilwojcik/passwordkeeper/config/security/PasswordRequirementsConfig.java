package pl.kamilwojcik.passwordkeeper.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kamilwojcik.passwordkeeper.passwords_storage.dto.PasswordRequirements;

@Configuration
public class PasswordRequirementsConfig {

    @Bean
    public PasswordRequirements getPasswordRequirements() {
        return new PasswordRequirements(12, 3, 1, 2, 1);
    }

}
