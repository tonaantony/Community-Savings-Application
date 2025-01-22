package com.ust.userManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.userManagement.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);

    boolean existsByUsername(@NotBlank(message = "Name cannot be blank") @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters") String name);

    boolean existsByEmail(@NotBlank(message = "Email cannot be blank") @Email(message = "Email should be valid") String email);
}
