package com.ust.userManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ust.userManagement.model.ResetToken;
import com.ust.userManagement.model.User;
import com.ust.userManagement.repository.ResetTokenRepository;
import com.ust.userManagement.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResetTokenRepository resetTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public String register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "User already exists in the system";
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return "User already exists in the system";
        }

        try {
            // Encode password
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // Save user to repository
            userRepository.save(user);
            return "User added to system";
        } catch (Exception e) {
            // In case of an error, return error message
            return "Error: User not added to the system. Please try again.";
        }
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    // Method to store the reset token (this could also store the expiration time)
    public void storeResetToken(String username, String resetToken) {
        // Assuming we have a ResetToken entity to store the token and username
        ResetToken token = new ResetToken();
        token.setUsername(username);
        token.setResetToken(resetToken);
        resetTokenRepository.save(token);
    }

    // Method to validate the reset token (checking expiration or validity)
    public boolean validateResetToken(String resetToken) {
        // Simple validation by checking if the token exists in the repository
        ResetToken token = resetTokenRepository.findByResetToken(resetToken);
        if (token == null) {
            return false;
        }

        // You can implement an expiration check here if needed
        return true;
    }

    // Find the user associated with the reset token
    public User findUserByResetToken(String resetToken) {
        ResetToken token = resetTokenRepository.findByResetToken(resetToken);
        if (token != null) {
            return userRepository.findByUsername(token.getUsername()).orElse(null);
        }
        return null;
    }

    // Method to update user details (e.g., password)
    public String updateUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "Password updated";
        }catch (Exception e){
            return "Error: User not added to the system. Please try again.";
        }
    }
}
