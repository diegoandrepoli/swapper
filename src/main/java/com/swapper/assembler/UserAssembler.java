package com.swapper.assembler;

import com.swapper.dto.RegisterRequestDTO;
import com.swapper.entities.User;
import com.swapper.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {

    public User to(RegisterRequestDTO registerRequestDTO, String password) {
        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(password);
        user.addRole(Role.USER);
        user.setEnabled(true);
        return user;
    }
}
