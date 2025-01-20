package com.communitySavings.fundManagement.controller;


import com.communitySavings.fundManagement.model.Contribution;
import com.communitySavings.fundManagement.service.ContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contributions")
public class ContributionController {
    @Autowired
    private ContributionService contributionService;

    @PostMapping
    public ResponseEntity<Contribution> addContribution(@RequestBody Contribution contribution) {
        Contribution savedContribution = contributionService.saveContribution(contribution);
        return ResponseEntity.ok(savedContribution);
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<Contribution>> getContributionsByGroup(@PathVariable Long groupId) {
        return ResponseEntity.ok(contributionService.getContributionsByGroupId(groupId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Contribution>> getContributionsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(contributionService.getContributionsByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<Contribution>> getAllContributions() {
        return ResponseEntity.ok(contributionService.getContributions());
    }
}
