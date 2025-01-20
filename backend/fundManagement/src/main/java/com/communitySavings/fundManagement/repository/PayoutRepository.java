package com.communitySavings.fundManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.communitySavings.fundManagement.model.Payout;

@Repository
public interface PayoutRepository extends JpaRepository<Payout, Long>{
    List<Payout> findByGroupId(Long groupId);
    List<Payout> findByUserId(Long userId);
}
