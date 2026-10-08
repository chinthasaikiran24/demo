package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailProducer emailProducer;

    public EmailController(EmailProducer emailProducer) {
        this.emailProducer = emailProducer;
    }

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest request)
            throws JsonProcessingException {

        emailProducer.sendEmailRequest(request);

        return "Email request sent to RabbitMQ";
    }
    
    @PostMapping("/notify")
    public String sendNotify(@RequestBody EmailRequest request) throws JsonProcessingException {
    	emailProducer.sendEmailNotif(request);
    	return "Email Notification sent to RabbitMQ";
    	
    }
}

/*                POST /email/send
                       │
                       ▼
                 Postman Request
                       │
                       ▼
                EmailController
                       │
                       ▼
                 EmailProducer
                       │
                       ▼
                RabbitMQ Exchange
                 email.exchange
                       │
              routing key: email.send
                       │
                       ▼
                  email.queue
                       │
                       ▼
                 EmailConsumer
                       │
                       ▼
                  EmailService
                       │
                       ▼
                JavaMailSender
                       │
                       ▼
                 Gmail SMTP
                       │
                       ▼
              Recipient's Inbox*/