package com.communitySavings.ai.controller;

import com.communitySavings.ai.model.FinancialSummary;
import com.communitySavings.ai.model.FundSuggestion;
import com.communitySavings.ai.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("/financial-summary")
    public ResponseEntity<FinancialSummary> generateFinancialSummary(@RequestBody String groupData) {
        FinancialSummary summary = aiService.generateFinancialSummary(groupData);
        return ResponseEntity.ok(summary);
    }

    @PostMapping("/fund-suggestions")
    public ResponseEntity<FundSuggestion> generateFundSuggestions(@RequestBody String groupData) {
        FundSuggestion suggestion = aiService.generateFundSuggestions(groupData);
        return ResponseEntity.ok(suggestion);
    }
}