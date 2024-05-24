package com.swapper.assembler;

import com.swapper.dto.RegisterDTO;
import com.swapper.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {

    public User to(RegisterDTO registerDTO) {
        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setName(registerDTO.getName());
        user.setPassword(registerDTO.getPassword());
        return user;
    }
}