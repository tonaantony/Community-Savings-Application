package com.ust.userManagement.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducerService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private static final String EXCHANGE = "userExchange";
    private static final String ROUTING_KEY = "user.routing.key";

    public void sendUserEvent(String message) {
        amqpTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, message);
    }
}

