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

    public void sendEmail(String emailAddress, String message) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(emailAddress);
        mail.setSubject("Message from Spring Boot");
        mail.setText(message);

        mailSender.send(mail);

        System.out.println(
                "Email sent successfully to: " + emailAddress
        );
    }
    
    public void sendEmailNotify(String emailAddress, String message) {
    	SimpleMailMessage msg = new SimpleMailMessage();
    	
    	msg.setTo(emailAddress);
    	msg.setFrom("Spring-boot-learner@gmail.com");
    	msg.setCc("chinthasaikiran3@gmail.com");
    	msg.setSubject("Reg Your Springboot Learning");
    	msg.setText(message);
    	msg.setReplyTo("chintakiran8@gmail.com");
    	
    	mailSender.send(msg);
    	System.out.println(
                "Notification email sent successfully to: " + emailAddress
        );
    }
}