package com.communitySavings.fundManagement.integration;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.communitySavings.fundManagement.config.RabbitMQConfig;

@Service
public class FundManagementRabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public FundManagementRabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendUserMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, "user.events", message);
    }

    public void sendGroupMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, "group.events", message);
    }
}
