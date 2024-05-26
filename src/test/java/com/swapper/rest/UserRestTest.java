package com.swapper.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapper.assembler.UserAssembler;
import com.swapper.dto.LoginRequestDTO;
import com.swapper.dto.PasswordRequestDTO;
import com.swapper.dto.RegisterRequestDTO;
import com.swapper.entities.User;
import com.swapper.repository.UserRepository;
import com.swapper.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class UserRestTest {

    private static final String USERNAME = "myusername";

    private static final String EMAIL = "fake@email.com";

    private static final String PASSWORD = "123456789";

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserAssembler userAssembler;

    @Autowired
    private UserRepository userRepository;

    private RegisterRequestDTO registerRequestDTO;

    private LoginRequestDTO loginRequestDTO;

    private PasswordRequestDTO passwordRequestDTO;

    private void setupAuthUser() {
        User user = new User();
        user.setEnabled(true);
        user.setUsername(USERNAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        userRepository.save(user);
    }

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        setupAuthUser();

        this.registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setUsername(USERNAME);
        registerRequestDTO.setEmail(EMAIL);
        registerRequestDTO.setPassword(PASSWORD);

        this.loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUsername(USERNAME);
        loginRequestDTO.setPassword(PASSWORD);

        this.passwordRequestDTO = new PasswordRequestDTO();
        passwordRequestDTO.setPassword(USERNAME);
        passwordRequestDTO.setNewPassword(PASSWORD);
    }

    protected String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @Test
    public void registerTest() throws Exception {
        String uri = "/api/v1/account/register";

        String registerRequestDTODtoAsString = mapToJson(this.registerRequestDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .content(registerRequestDTODtoAsString)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());

        verify(userService, timeout(1)).register(any());
    }

    @Test
    public void loginTest() throws Exception {
        String uri = "/api/v1/account/login";

        String loginRequestDTOAsString = mapToJson(this.loginRequestDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .content(loginRequestDTOAsString)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());

        verify(userService, timeout(1)).authenticate(any());
    }

    @Test
    @WithUserDetails(USERNAME)
    public void passwordTest() throws Exception {
        String uri = "/api/v1/account/password";

        String passwordRequestDTOAsString = mapToJson(this.passwordRequestDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .content(passwordRequestDTOAsString)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());

        verify(userService, timeout(1)).password(any(), any());
    }
}
