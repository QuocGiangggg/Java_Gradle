package vn.hoidanit.jobhunter.service;

import org.springframework.stereotype.Service;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@Service
public class EmailService {

    private final MailSender mailSender;

    public EmailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("dangquocgiang2002@gmail.com");
        msg.setSubject("Testting from SpringBoot");
        msg.setText("Hello word SpringBoot Email");
        this.mailSender.send(msg);
    }
}
