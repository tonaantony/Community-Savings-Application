package com.communitySavings.fundManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.communitySavings.fundManagement.model.Contribution;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long>{
    List<Contribution> findByGroupId(Long groupId);
    List<Contribution> findByUserId(Long userId);
}
