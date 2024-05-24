package com.swapper.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapper.assembler.UserAssembler;
import com.swapper.dto.LoginDTO;
import com.swapper.dto.PasswordDTO;
import com.swapper.dto.RegisterDTO;
import com.swapper.entities.User;
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

    private RegisterDTO registerDTO;

    private  User user;

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        String name = "my name";
        String email = "fake@email.com";
        String password = "123456789";

        this.registerDTO = new RegisterDTO();
        registerDTO.setName(name);
        registerDTO.setEmail(email);
        registerDTO.setPassword(password);

        this.user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
    }

    protected String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @Test
    public void registerTest() throws Exception {
        String uri = "/api/v1/account/register";

        String registerDtoAsString = mapToJson(registerDTO);
        when(userAssembler.to(any())).thenReturn(user);

        this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .content(registerDtoAsString)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());

        verify(userService, timeout(1)).register(user);
    }

    @Test
    public void loginTest() throws Exception {
        String uri = "/api/v1/account/login";

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("fake@email.com");
        loginDTO.setPassword("123456789");
        String loginDTOAsString = mapToJson(loginDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .content(loginDTOAsString)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());
    }

    @Test
    public void passwordTest() throws Exception {
        String uri = "/api/v1/account/password";

        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setPassword("123456789");
        passwordDTO.setNewPassword("987654321");
        String passwordDTOAsString = mapToJson(passwordDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .content(passwordDTOAsString)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());
    }
}
