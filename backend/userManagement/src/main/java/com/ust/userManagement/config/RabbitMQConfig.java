package com.ust.userManagement.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue userQueue() {
        return new Queue("userQueue", false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("userExchange");
    }

    @Bean
    public Binding binding(org.springframework.amqp.core.Queue userQueue, TopicExchange exchange) {
        return BindingBuilder.bind(userQueue).to(exchange).with("user.routing.key");
    }
}

