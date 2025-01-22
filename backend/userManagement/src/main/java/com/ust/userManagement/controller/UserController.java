package com.ust.userManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ust.userManagement.dto.AuthRequest;
import com.ust.userManagement.dto.PasswordChangeDto;
import com.ust.userManagement.model.User;
import com.ust.userManagement.service.JwtService;
import com.ust.userManagement.service.KafkaProducerService;
import com.ust.userManagement.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private KafkaProducerService kafkaProducerService;
    @PostMapping("/register")
    public String register(@RequestBody  @Valid User user){

        try {
            // String message = "User registered: " + user.getUsername();
            // kafkaProducerService.sendUserEvent(message);
            return userService.register(user);
        } catch (Exception e) {
            return "Error while adding user: " + e.getMessage();
        }
    }
    

    //login endpoint
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(authRequest.getUsername());
            } else {
                throw new UsernameNotFoundException("Authentication failed: invalid username or password.");
            }
        } catch (UsernameNotFoundException e) {
            return "Authentication failed: " + e.getMessage();
        } catch (Exception e) {
            return "An error occurred during authentication: " + e.getMessage();
        }


    }

    // Step 1: Forgot Password - Request Reset Token
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody AuthRequest authRequest) {
        try {
            // Step 2: Check if user exists based on their username (or email)
            User user = userService.findByUsername(authRequest.getUsername());
            if (user == null) {
                return "User not found with username: " + authRequest.getUsername();
            }

            // Step 3: Generate a reset token (for simplicity, using UUID here)
            String resetToken = generateResetToken();

            // Step 4: Store this reset token (for example in the database, with expiry time)
            userService.storeResetToken(authRequest.getUsername(), resetToken);

            return "Reset token generated successfully: " + resetToken;
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage();
        }
    }

    // Step 2: Reset Password - Using the Reset Token
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String resetToken, @RequestBody @Valid PasswordChangeDto passwordRequest) {
        try {
            // Step 5: Validate the reset token (e.g., check if it's expired)
            boolean isValidToken = validateResetToken(resetToken);
            if (!isValidToken) {
                return "Invalid or expired reset token.";
            }

            // Step 6: Find the user associated with the token
            User user = userService.findUserByResetToken(resetToken);
            if (user == null) {
                return "User not found for the provided reset token.";
            }

                // Step 7: Update the user's password (ensure to hash it before saving)
                //user.setPassword(passwordEncoder.encode(user.getPassword()));
                String newPassword = passwordRequest.getPassword();
                user.setPassword(newPassword);  // Ideally, you should hash this password using BCrypt
                return userService.updateUser(user);


        } catch (Exception e) {
            return "Error resetting password: " + e.getMessage();
        }
    }

    // Utility method to generate a reset token (e.g., UUID)
    private String generateResetToken() {
        return java.util.UUID.randomUUID().toString();  // Simple random string token
    }

    // Utility method to validate a reset token (e.g., check expiration)
    private boolean validateResetToken(String resetToken) {
        // Implement validation logic such as checking expiration or token format.
        // For simplicity, we'll return true in this example.
        return true;
    }

}
