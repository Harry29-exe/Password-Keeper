package pl.kamilwojcik.passwordkeeper.config.email;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Profile("dev")
@Service
public class MockEmailService implements EmailService {

    private final Logger logger = Logger.getLogger(EmailService.class.getName());

    @Override
    public void sendEmail(String title, String emailContent, String sendTo) {
        logger.info("Email msg to: " + sendTo + " with following content: " + emailContent);
    }
}
