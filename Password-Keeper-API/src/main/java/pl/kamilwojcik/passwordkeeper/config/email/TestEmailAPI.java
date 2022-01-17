package pl.kamilwojcik.passwordkeeper.config.email;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("dev")
@RestController
public class TestEmailAPI {
    private final EmailService emailService;

    public TestEmailAPI(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("send-test-email")
    public void sendTestMail() {
        emailService.sendEmail("test mail", "some content <h1>Is this working? </h1>", "wojcikakamil@gmail.com");
    }

}
