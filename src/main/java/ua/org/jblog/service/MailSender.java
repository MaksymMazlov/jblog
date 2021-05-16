package ua.org.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender
{
    @Autowired
    public JavaMailSender emailSender;
    @Value("${app.mail.from-email}")
    private String fromEmail;
    public void sendEmail(String emailTo, String subject, String message)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        emailSender.send(mailMessage);
    }
}
