package com.example.demo;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendRegistrationMail(String toEmail) {

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setFrom("chinthasaikiran24@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Registration Successful");
        message.setText("Your Email_id has been successfully registered"
        );

        mailSender.send(message);
    }
}