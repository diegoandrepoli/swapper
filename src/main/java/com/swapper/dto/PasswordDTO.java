package com.swapper.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class PasswordDTO {

    @Length(min = 8, max = 50, message = "Senha deve conter entre 8 e 50 caracteres")
    @NotBlank(message = "Informe sua senha")
    private String password;

    @Length(min = 8, max = 50, message = "Nova senha deve conter entre 8 e 50 caracteres")
    @NotBlank(message = "Informe sua nova senha")
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
