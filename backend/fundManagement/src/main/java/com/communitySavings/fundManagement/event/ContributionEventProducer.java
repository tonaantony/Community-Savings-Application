package com.communitySavings.fundManagement.event;



import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.communitySavings.fundManagement.model.Contribution;

@Component
public class ContributionEventProducer {
    
    private final KafkaTemplate<String, Contribution> kafkaTemplate;

    public ContributionEventProducer(KafkaTemplate<String, Contribution> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishContributionEvent(Contribution contribution) {
        kafkaTemplate.send("contribution-events", contribution);
    }
}

