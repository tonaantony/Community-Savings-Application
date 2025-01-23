package com.communitySavings.ai.dto;

import java.util.List;

public class FundSuggestionRequest {

    private Long groupId;
    private List<Contribution> contributions;
    private Double totalFund;

    // Inner static class for Contribution
    public static class Contribution {
        private Long userId;
        private Double amount;

        // Getters and Setters
        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }
    }

    // Getters and Setters
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<Contribution> getContributions() {
        return contributions;
    }

    public void setContributions(List<Contribution> contributions) {
        this.contributions = contributions;
    }

    public Double getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(Double totalFund) {
        this.totalFund = totalFund;
    }
}
