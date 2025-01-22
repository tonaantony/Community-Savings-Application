package com.community_Savings.GroupManagement.dto;

import lombok.Data;

@Data
public class GroupDTO {
    private Long groupId;
    private String groupName;
    private Long organizerId;
    private String organizerName;
    private String groupRules;
    private boolean approved;

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

    public Long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public String getGroupRules() {
        return groupRules;
    }

    public void setGroupRules(String groupRules) {
        this.groupRules = groupRules;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
