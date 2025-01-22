package com.community_Savings.GroupManagement.repository;

import com.community_Savings.GroupManagement.entity.GroupMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupMembershipRepository extends JpaRepository<GroupMembership, Long> {
    List<GroupMembership> findByUserId(Long userId);

    List<GroupMembership> findByGroupId(Long groupId);
}
