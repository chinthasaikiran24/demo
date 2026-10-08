package com.example.demo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public EmailProducer(
            RabbitTemplate rabbitTemplate,
            ObjectMapper objectMapper) {

        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendEmailRequest(EmailRequest request)
            throws JsonProcessingException {

        String message = objectMapper.writeValueAsString(request);

        rabbitTemplate.convertAndSend(
                "email.exchange",
                "email.send",
                message
        );
    }
    
    public void sendEmailNotif(EmailRequest request) throws JsonProcessingException{
    	
    	String message = objectMapper.writeValueAsString(request);
    	
    	rabbitTemplate.convertAndSend(
    			"email-notify-exchange",
    			"email-notify-roll",
    			message
    			
    			);
    	
    }
}