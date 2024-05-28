package com.swapper.assembler;

import com.swapper.dto.RegisterRequestDTO;
import com.swapper.entities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserAssemblerTest {

    public static final String USERNAME = "myusername";

    public static final String EMAIL = "fake@email.com";

    public static final String PASSWORD = "123456789";

    @Test
    public void testFromRegisterRequestDTO() {
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setUsername(USERNAME);
        registerRequestDTO.setEmail(EMAIL);
        registerRequestDTO.setPassword(PASSWORD);

        UserAssembler userAssembler = new UserAssembler();
        User user = userAssembler.to(registerRequestDTO, PASSWORD);

        assertEquals(user.getUsername(),USERNAME);
        assertEquals(user.getEmail(),EMAIL);
        assertEquals(user.getPassword(),PASSWORD);
        assertEquals(user.getRoles().size(), 1);
        assertTrue(user.isEnabled());
    }
}