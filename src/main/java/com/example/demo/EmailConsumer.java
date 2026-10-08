package com.example.demo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EmailConsumer {

    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    public EmailConsumer(
            ObjectMapper objectMapper,
            EmailService emailService) {

        this.objectMapper = objectMapper;
        this.emailService = emailService;
    }

    @RabbitListener(queues = "email.queue")
    public void receiveEmailMessage(String message) {

        try {

            EmailRequest request =
                    objectMapper.readValue(message, EmailRequest.class);

            System.out.println(
                    "Received email request for: "
                    + request.getEmailAddress()
            );

            emailService.sendEmail(
                    request.getEmailAddress(),
                    request.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed to process email: "
                    + e.getMessage()
            );
        }
    }
    
    @RabbitListener(queues = "email-notify-queue")
    public void receivedEmailNotif(String message)
    {
    	try {
    		EmailRequest read = objectMapper.readValue(message, EmailRequest.class);
    		System.out.println("received message email "+read.getEmailAddress());
    		System.out.println("Received message "+read.getMessage());
    		System.out.println("Received message "+read.getSubject());
    		emailService.sendEmailNotify(read.getEmailAddress(),
    				read.getMessage(),read.getSubject());
    		
    	}catch (Exception e) {

            System.out.println(
                    "Failed to process email: "
                    + e.getMessage()
            );
        }
    }
}