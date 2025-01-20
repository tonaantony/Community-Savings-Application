package com.communitySavings.fundManagement.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.communitySavings.fundManagement.model.Contribution;
import com.communitySavings.fundManagement.service.ContributionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ContributionEventConsumer {

    private final ContributionService contributionService;
    private final ObjectMapper objectMapper;

    public ContributionEventConsumer(ContributionService contributionService, ObjectMapper objectMapper) {
        this.contributionService = contributionService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "contribution-events", groupId = "fund-management-service")
    public void handleContributionEvent(String message) {
        try {
            // Deserialize the message into a Contribution object
            Contribution contribution = objectMapper.readValue(message, Contribution.class);
            
            // Log the received contribution
            System.out.println("Received Contribution Event: " + contribution);
            
            // Save the contribution to the database
            contributionService.saveContribution(contribution);

            // Perform any additional logic (e.g., notifications or triggering payouts)

        } catch (Exception e) {
            // Handle deserialization or processing errors
            System.err.println("Error processing contribution event: " + e.getMessage());
        }
    }
}
