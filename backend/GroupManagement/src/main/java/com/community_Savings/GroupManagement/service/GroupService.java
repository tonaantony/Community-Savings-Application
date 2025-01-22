package com.community_Savings.GroupManagement.service;


import com.community_Savings.GroupManagement.config.RabbitMQConfig;
import com.community_Savings.GroupManagement.dto.GroupDTO;
import com.community_Savings.GroupManagement.dto.GroupMembershipDTO;
import com.community_Savings.GroupManagement.entity.Group;
import com.community_Savings.GroupManagement.entity.GroupMembership;
import com.community_Savings.GroupManagement.repository.GroupMembershipRepository;
import com.community_Savings.GroupManagement.repository.GroupRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private static final String GROUP_TOPIC = "group-management-events";

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMembershipRepository groupMembershipRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Create a new group
    public Group createGroup(GroupDTO groupDTO) {
        Group group = new Group();
        group.setGroupId(groupDTO.getGroupId());
        group.setGroupName(groupDTO.getGroupName());
        group.setOrganizerId(groupDTO.getOrganizerId());
        group.setOrganizerName(groupDTO.getOrganizerName());
        group.setGroupRules(groupDTO.getGroupRules());
        group.setApproved(false);

        Group savedGroup = groupRepository.save(group);

        kafkaTemplate.send(GROUP_TOPIC, "Group Created: " + groupDTO.toString());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, groupDTO);
        return savedGroup;
    }

    // Add a member to a group
    public GroupMembership addMember(GroupMembershipDTO groupMembershipDTO) {
        GroupMembership membership = new GroupMembership();
        membership.setGroupId(groupMembershipDTO.getGroupId());
        membership.setUserId(groupMembershipDTO.getUserId());

        GroupMembership savedMembership = groupMembershipRepository.save(membership);

        kafkaTemplate.send(GROUP_TOPIC, "Member Added: " + groupMembershipDTO.toString());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, groupMembershipDTO);
        return savedMembership;
    }

    // List all groups for a specific user
    public List<Group> listGroupsForUser(Long userId) {
        List<GroupMembership> memberships = groupMembershipRepository.findByUserId(userId);
        return memberships.stream()
                .map(membership -> {
                    Optional<Group> group = groupRepository.findById(membership.getGroupId());
                    return group.orElse(null);
                })
                .toList();
    }

    // List all users in a specific group
    public List<GroupMembership> listUsersInGroup(Long groupId) {
        return groupMembershipRepository.findByGroupId(groupId);
    }
}


