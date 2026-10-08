package com.example.demo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

	@RabbitListener(queues="employee.queue")
	
	public void receiveMessage(String message) {
		System.out.println("Message is received to RabbitMq "+message);
	}
	
	
}
