package com.swapper.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class PasswordRequestDTO {

    @Length(min = 8, max = 50, message = "Password must contain between 8 and 50 characters")
    @NotBlank(message = "Inform your password")
    private String password;

    @Length(min = 8, max = 50, message = "New password must contain between 8 and 50 characters")
    @NotBlank(message = "Enter your new password")
    private String newPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
