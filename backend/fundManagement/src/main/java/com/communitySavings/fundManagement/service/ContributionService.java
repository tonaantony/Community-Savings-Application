package com.communitySavings.fundManagement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.communitySavings.fundManagement.model.Contribution;
import com.communitySavings.fundManagement.repository.ContributionRepository;

import java.util.List;

@Service
public class ContributionService {
    @Autowired
    private ContributionRepository contributionRepository;

    public Contribution saveContribution(Contribution contribution) {
        return contributionRepository.save(contribution);
    }

    public List<Contribution> getContributionsByGroupId(Long groupId) {
        return contributionRepository.findByGroupId(groupId);
    }

    public List<Contribution> getContributionsByUserId(Long userId) {
        return contributionRepository.findByUserId(userId);
    }

    public List<Contribution> getContributions() {
        return contributionRepository.findAll();
    }
}

