package com.swapper;

import com.swapper.assembler.UserAssembler;
import com.swapper.dto.RegisterDTO;
import com.swapper.entities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAssemblerTest {

    @Test
    public void toRegisterDTOTest() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setName("my name");
        registerDTO.setEmail("my@email.com");
        registerDTO.setPassword("123456789");

        UserAssembler userAssembler = new UserAssembler();
        User user = userAssembler.to(registerDTO);

        assertEquals(registerDTO.getName(), user.getName());
        assertEquals(registerDTO.getEmail(), user.getEmail());
        assertEquals(registerDTO.getPassword(), user.getPassword());
    }
}