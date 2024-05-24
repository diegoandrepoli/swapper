package com.swapper.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class RegisterDTO {

    @Email(message = "E-mail não é válido")
    @NotBlank(message = "Informe seu E-mail")
    private String email;

    @Length(min = 8, max = 50, message = "Senha deve conter entre 8 e 50 caracteres")
    @NotBlank(message = "Informe a sua senha")
    private String password;

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
}
