
package com.communitySavings.ai.service;

import com.communitySavings.ai.model.FinancialSummary;
import com.communitySavings.ai.model.FundSuggestion;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class AIService {

    @Value("${huggingface.api.key}")
    private String apiKey;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient geminiWebClient;
    private final WebClient huggingFaceWebClient;
    private final ObjectMapper objectMapper;

    public AIService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.geminiWebClient = webClientBuilder.baseUrl("https://generativelanguage.googleapis.com/v1beta").build();
        this.huggingFaceWebClient = webClientBuilder.baseUrl("https://api-inference.huggingface.co").build();
        this.objectMapper = objectMapper;
    }

    public FinancialSummary generateFinancialSummary(String groupData) {
        String response = huggingFaceWebClient.post()
                .uri("/models/facebook/bart-large-cnn")  // Replace with your Hugging Face model
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(groupData)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Parse the response into FinancialSummary
        return new FinancialSummary(response);
    }

    /**
     * Generate fund suggestions for a given group data using the Gemini API
     *
     * @param groupData the group data (usually a JSON representation of contributions and total fund)
     * @return the generated fund suggestion
     */
    public FundSuggestion generateFundSuggestions(String groupData) {
        try {

            String payload = String.format(
                    """
                    {
                      "contents": [
                        {
                          "parts": [
                            {
                              "text": "%s" 
                            }
                          ]
                        }
                      ]
                    }
                    """, groupData); // Corrected the missing comma

            String response = geminiWebClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .path("/models/gemini-1.5-flash:generateContent")
                            .queryParam("key", geminiApiKey)
                            .build())
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .bodyValue(payload)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            JsonNode jsonNode = objectMapper.readTree(response);
            if (jsonNode.has("candidates") && jsonNode.get("candidates").isArray() && jsonNode.get("candidates").size() > 0
                    && jsonNode.get("candidates").get(0).has("content") && jsonNode.get("candidates").get(0).get("content").has("parts")
                    && jsonNode.get("candidates").get(0).get("content").get("parts").isArray() && jsonNode.get("candidates").get(0).get("content").get("parts").size() > 0
                    && jsonNode.get("candidates").get(0).get("content").get("parts").get(0).has("text")) {
                String suggestion = jsonNode.get("candidates").get(0).get("content").get("parts").get(0).get("text").asText();
                return new FundSuggestion(suggestion);
            } else {
                return new FundSuggestion("Error processing the Gemini Response");
            }
        } catch (WebClientResponseException e) {
            System.err.println("Error calling Gemini API: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
            return new FundSuggestion("Error generating fund suggestions.");
        } catch (IOException e) {
            System.err.println("Error parsing Gemini API response: " + e.getMessage());
            return new FundSuggestion("Error generating fund suggestions.");
        }
    }
}