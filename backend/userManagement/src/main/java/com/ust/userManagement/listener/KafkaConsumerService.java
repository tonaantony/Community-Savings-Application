package com.ust.userManagement.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "user-events", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Received message: " + message);
    }
}

