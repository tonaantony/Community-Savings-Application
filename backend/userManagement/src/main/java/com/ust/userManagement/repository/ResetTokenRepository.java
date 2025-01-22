package com.ust.userManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.userManagement.model.ResetToken;

@Repository
public interface ResetTokenRepository extends JpaRepository<ResetToken, String> {
    ResetToken findByResetToken(String resetToken);
}
