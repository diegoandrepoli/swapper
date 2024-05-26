package com.swapper.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class LoginRequestDTO {

    @Length(min = 8, max = 50, message = "Username must contain between 5 and 50 characters")
    @NotBlank(message = "Enter your username")
    private String username;

    @Length(min = 8, max = 50, message = "Password must contain between 8 and 50 characters")
    @NotBlank(message = "Enter your password")
    private String password;

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