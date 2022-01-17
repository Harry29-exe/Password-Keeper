package pl.kamilwojcik.passwordkeeper.config.email;

public interface EmailService {

    void sendEmail(String title, String emailContent, String sendTo);

}
