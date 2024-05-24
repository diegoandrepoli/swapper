package com.swapper.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapper.dto.LoginDTO;
import com.swapper.dto.PasswordDTO;
import com.swapper.dto.RegisterDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AccountRest.class)
@ContextConfiguration(classes = {AccountRest.class})
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @Test
    public void registerTest() throws Exception {
        String uri = "/api/v1/account/register";

        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmail("fake@email.com");
        registerDTO.setPassword("123456789");
        String registerDtoAsString = mapToJson(registerDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .content(registerDtoAsString)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());
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
