package com.community_Savings.GroupManagement.controller;

import com.community_Savings.GroupManagement.dto.GroupDTO;
import com.community_Savings.GroupManagement.dto.GroupMembershipDTO;
import com.community_Savings.GroupManagement.entity.Group;
import com.community_Savings.GroupManagement.entity.GroupMembership;
import com.community_Savings.GroupManagement.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    // Endpoint to create a group
    @PostMapping("/create")
    public Group createGroup(@RequestBody GroupDTO groupDTO) {
        return groupService.createGroup(groupDTO);
    }

    // Endpoint to add a member to a group
    @PostMapping("/{groupId}/members")
    public GroupMembership addMember(@PathVariable Long groupId, @RequestBody GroupMembershipDTO groupMembershipDTO) {
        groupMembershipDTO.setGroupId(groupId);
        return groupService.addMember(groupMembershipDTO);
    }

    // Endpoint to list all groups for a user
    @GetMapping
    public List<Group> listGroupsForUser(@RequestParam Long userId) {
        return groupService.listGroupsForUser(userId);
    }

    // Endpoint to list all members in a specific group
    @GetMapping("/{groupId}/members")
    public List<GroupMembership> listUsersInGroup(@PathVariable Long groupId) {
        return groupService.listUsersInGroup(groupId);
    }
}


