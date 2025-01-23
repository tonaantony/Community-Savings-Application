package com.communitySavings.ai.model;


public class FundSuggestion {
    private String suggestion;
    
    public FundSuggestion() {
    }

    public FundSuggestion(String response) {
        this.suggestion = response;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
    
}
