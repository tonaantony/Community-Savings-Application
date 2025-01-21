package com.communitySavings.fundManagement.integration;

import com.communitySavings.fundManagement.dto.Group;
import com.communitySavings.fundManagement.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FundManagementIntegrationService {

    private static final Logger logger = LoggerFactory.getLogger(FundManagementIntegrationService.class);

    @Value("${user.management.service.url}")
    private String userManagementServiceUrl;

    @Value("${group.management.service.url}")
    private String groupManagementServiceUrl;

    private final RestTemplate restTemplate;

    public FundManagementIntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Group> getGroups() {
        String url = groupManagementServiceUrl + "/groups";
        logger.info("Fetching groups from URL: {}", url);

        try {
            ResponseEntity<List<Group>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Group>>() {}
            );
            return response.getBody();
        } catch (RestClientException e) {
            logger.error("Error fetching groups: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch groups from Group Management Service", e);
        }
    }

    public User getUserDetails(Long userId) {
        String url = userManagementServiceUrl + "/users/" + userId;
        logger.info("Fetching user details for ID: {} from URL: {}", userId, url);

        try {
            ResponseEntity<User> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                User.class
            );
            return response.getBody();
        } catch (RestClientException e) {
            logger.error("Error fetching user details: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch user details from User Management Service", e);
        }
    }
}
