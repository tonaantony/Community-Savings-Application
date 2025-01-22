package com.ust.userManagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String username;

    @Column(unique = true)
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Pattern(
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",
            message = "Password must include at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    private String password;
    @NotBlank(message = "Roles cannot be blank")
    @Pattern(regexp = "^(ADMIN|ORGANIZER|USER)$", message = "Role must be one of ADMIN or ORGANIZER or USER")
    private String roles;
    public User() {
    }
    public User(Long userId,
            @NotBlank(message = "Name cannot be blank") @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters") String username,
            @NotBlank(message = "Email cannot be blank") @Email(message = "Email should be valid") String email,
            @NotBlank(message = "Password cannot be blank") @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Password must include at least one uppercase letter, one lowercase letter, one number, and one special character") String password,
            @NotBlank(message = "Roles cannot be blank") @Pattern(regexp = "^(ROLE_ADMIN|ROLE_USER)$", message = "Role must be one of ROLE_ADMIN or ROLE_USER") String roles) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }
    
}
