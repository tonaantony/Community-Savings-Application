package com.communitySavings.ai.model;


public class FinancialSummary {
    private String summary;
    
    public FinancialSummary() {
    }

    public FinancialSummary(String response) {
        this.summary = response;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    
}

