package com.communitySavings.fundManagement.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String USER_QUEUE = "user.queue";
    public static final String GROUP_QUEUE = "group.queue";
    public static final String EXCHANGE = "community.exchange";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(USER_QUEUE);
    }

    @Bean
    public Queue groupQueue() {
        return new Queue(GROUP_QUEUE);
    }

    @Bean
    public Binding userQueueBinding(Queue userQueue, TopicExchange exchange) {
        return BindingBuilder.bind(userQueue).to(exchange).with("user.#");
    }

    @Bean
    public Binding groupQueueBinding(Queue groupQueue, TopicExchange exchange) {
        return BindingBuilder.bind(groupQueue).to(exchange).with("group.#");
    }
}
