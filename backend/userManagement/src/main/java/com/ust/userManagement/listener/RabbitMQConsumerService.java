package com.ust.userManagement.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumerService {

    @RabbitListener(queues = "userQueue")
    public void consume(String message) {
        System.out.println("Received message: " + message);
    }
}

