package ua.org.jblog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig
{
    @Value("${app.mail.from-email}")
    private String fromEmail;
    @Value("${app.mail.from-password}")
    private String fromPassword;
    @Value("${app.mail.host}")
    private String host;
    @Value("${app.mail.port}")
    private int port;
    @Value("${app.mail.transport.protocol}")
    private String protocol;
    @Value("${app.mail.smtp.auth}")
    private String auth;
    @Value("${app.mail.smtp.starttls.enable}")
    private String starttlsEnable;
    @Value("${app.mail.debug}")
    private String debug;
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(fromEmail);
        mailSender.setPassword(fromPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttlsEnable);
        props.put("mail.debug", debug);

        return mailSender;
    }
}
