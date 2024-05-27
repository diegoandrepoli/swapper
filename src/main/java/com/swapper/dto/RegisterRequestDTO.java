package com.swapper.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class RegisterRequestDTO {

    @Email(message = "Email is not valid")
    @NotBlank(message = "Enter your email")
    private String email;

    @Length(min = 5, max = 50, message = "Name must be between 3 and 50 characters")
    @NotBlank(message = "Enter your name")
    private String username;

    @Length(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    @NotBlank(message = "Enter your password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}