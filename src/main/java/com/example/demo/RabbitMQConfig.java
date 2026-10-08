package com.example.demo;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // =========================
    // EMPLOYEE RABBITMQ
    // =========================

    @Bean
    public DirectExchange employeeExchange() {
        return new DirectExchange("employee.exchange");
    }

    @Bean
    public Queue employeeQueue() {
        return new Queue("employee.queue");
    }

    @Bean
    public Binding employeeBinding(
            Queue employeeQueue,
            DirectExchange employeeExchange) {

        return BindingBuilder
                .bind(employeeQueue)
                .to(employeeExchange)
                .with("employee.created");
    }


    // =========================
    // EMAIL RABBITMQ
    // =========================

    @Bean
    public DirectExchange emailExchange() {
        return new DirectExchange("email.exchange");
    }

    @Bean
    public Queue emailQueue() {
        return new Queue("email.queue");
    }

    @Bean
    public Binding emailBinding(
            Queue emailQueue,
            DirectExchange emailExchange) {

        return BindingBuilder
                .bind(emailQueue)
                .to(emailExchange)
                .with("email.send");
    }
    
    @Bean
    public DirectExchange emailNotif() {
    return new DirectExchange("email-notify-exchange");
    }
    
    @Bean
    public Queue EmailNotifQueue()
    {
     return new Queue("email-notify-queue");	
    }
    
    @Bean
    public Binding emailNotifyBinding(Queue EmailNotifQueue,DirectExchange emailNotif) {
    	 
    	return BindingBuilder
    			.bind(EmailNotifQueue).
    			to(emailNotif).
    			with("email-notify-roll");
    	
    }
    
 // SMS
    @Bean
    public DirectExchange smsExchange() {
        return new DirectExchange("sms.exchange");
    }

    @Bean
    public Queue smsQueue() {
        return new Queue("sms.queue");
    }

    @Bean
    public Binding smsBinding(
            Queue smsQueue,
            DirectExchange smsExchange) {

        return BindingBuilder
                .bind(smsQueue)
                .to(smsExchange)
                .with("sms.send");
    }
}