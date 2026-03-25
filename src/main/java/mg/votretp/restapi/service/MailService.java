package mg.votretp.restapi.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void envoyerCodeValidation(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Validation de votre commande");
        message.setText(
                "Bonjour,\n\n" +
                        "Votre code de validation est : " + code + "\n\n" +
                        "Ce code expire dans 10 minutes.\n\n" +
                        "Merci."
        );

        mailSender.send(message);
    }
}