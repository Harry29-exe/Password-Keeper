package pl.kamilwojcik.passwordkeeper.config.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

//@Profile("!dev")
//@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String title, String emailContent, String sendTo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("cloudpivpn@gmail.com");
        message.setTo(sendTo);
        message.setSubject(title);
        message.setText(emailContent);

        mailSender.send(message);
    }
}
