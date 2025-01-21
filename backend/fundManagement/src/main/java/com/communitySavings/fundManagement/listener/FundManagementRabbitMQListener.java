package com.communitySavings.fundManagement.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.communitySavings.fundManagement.config.RabbitMQConfig;

@Service
public class FundManagementRabbitMQListener {

    @RabbitListener(queues = RabbitMQConfig.USER_QUEUE)
    public void handleUserMessage(String userMessage) {
        // Process the user message (e.g., user updates)
        System.out.println("Received User Message: " + userMessage);
        // Add logic to update Fund Management's state if necessary
    }

    @RabbitListener(queues = RabbitMQConfig.GROUP_QUEUE)
    public void handleGroupMessage(String groupMessage) {
        // Process the group message (e.g., group updates)
        System.out.println("Received Group Message: " + groupMessage);
        // Add logic to update Fund Management's state if necessary
    }
}
