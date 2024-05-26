package com.swapper.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapper.assembler.UserAssembler;
import com.swapper.dto.LoginRequestDTO;
import com.swapper.dto.PasswordRequestDTO;
import com.swapper.dto.RegisterRequestDTO;
import com.swapper.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserRest.class)
@ContextConfiguration(classes = {UserRest.class})
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    @MockBean
    private UserAssembler userAssembler;

    private RegisterRequestDTO registerRequestDTO;

    private LoginRequestDTO loginRequestDTO;

    private PasswordRequestDTO passwordRequestDTO;

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        String usermame = "myusername";
        String email = "fake@email.com";
        String password = "123456789";

        this.registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setUsername(usermame);
        registerRequestDTO.setEmail(email);
        registerRequestDTO.setPassword(password);

        this.loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUsername(usermame);
        loginRequestDTO.setPassword(password);

        this.passwordRequestDTO = new PasswordRequestDTO();
        passwordRequestDTO.setPassword(password);
        passwordRequestDTO.setNewPassword(password);
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
    public void passwordTest() throws Exception {
        String uri = "/api/v1/account/password";

        String passwordRequestDTOAsString = mapToJson(this.passwordRequestDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .content(passwordRequestDTOAsString)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());
    }
}
