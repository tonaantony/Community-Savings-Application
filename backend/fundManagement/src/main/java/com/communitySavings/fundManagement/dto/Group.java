package com.communitySavings.fundManagement.dto;

public class Group {
    private Long groupId;
    private String groupName;
    private String groupRules;
    public Long getGroupId() {
        return groupId;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getGroupRules() {
        return groupRules;
    }
    public void setGroupRules(String groupRules) {
        this.groupRules = groupRules;
    }
    
}
