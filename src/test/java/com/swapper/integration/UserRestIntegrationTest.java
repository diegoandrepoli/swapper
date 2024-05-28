package com.swapper.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapper.config.SwapiConfig;
import com.swapper.dto.LoginRequestDTO;
import com.swapper.dto.PasswordRequestDTO;
import com.swapper.dto.RegisterRequestDTO;
import com.swapper.entities.User;
import com.swapper.repository.UserRepository;
import com.swapper.service.UserService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class UserRestIntegrationTest {

    @Inject
    private UserService userService;

    @MockBean
    private WebApplicationContext webApplicationContext;

    @MockBean
    private SwapiConfig environmentConfig;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeTransaction
    public void setup() {
        User user = new User();
        user.setEnabled(true);
        user.setUsername("superuser");
        user.setEmail("superuser@yes.com");
        user.setPassword("superuser123456");

        this.userRepository.save(user);
    }

    @Test
    void registerUser() throws Exception {
        String uri = "/api/account/register";

        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setUsername("masteruser");
        registerRequestDTO.setEmail("masteruser@test.com");
        registerRequestDTO.setPassword("a1b2c3d4e5");

        String registerRequestDTODtoAsString = objectMapper.writeValueAsString(registerRequestDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .content(registerRequestDTODtoAsString)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());
    }

    @Test
    void registerFailUser() throws Exception {
        String uri = "/api/account/register";

        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setUsername("majoreruser");
        registerRequestDTO.setEmail("majoruser@test.com");
        registerRequestDTO.setPassword("123");

        String registerRequestDTODtoAsString = objectMapper.writeValueAsString(registerRequestDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .content(registerRequestDTODtoAsString)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().is(400));
    }

    @Test
    void loginUser() throws Exception {
        String registerUri = "/api/account/register";

        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setUsername("localuser");
        registerRequestDTO.setEmail("localuser@test.com");
        registerRequestDTO.setPassword("a1b2c3d4e5");

        String registerRequestDTODtoAsString = objectMapper.writeValueAsString(registerRequestDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(registerUri)
            .content(registerRequestDTODtoAsString)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());


        String loginUri = "/api/account/login";

        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUsername("localuser");
        loginRequestDTO.setPassword("a1b2c3d4e5");

        String loginRequestDTOtoAsString = objectMapper.writeValueAsString(loginRequestDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(loginUri)
            .content(loginRequestDTOtoAsString)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @WithUserDetails("superuser")
    void passwordUser() throws Exception {
        String uri = "/api/account/password";

        PasswordRequestDTO passwordRequestDTO = new PasswordRequestDTO();
        passwordRequestDTO.setPassword("superuser123456");
        passwordRequestDTO.setNewPassword("9876543210");

        String loginRequestDTOtoAsString = objectMapper.writeValueAsString(passwordRequestDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .content(loginRequestDTOtoAsString)
            .characterEncoding("utf-8")
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());
    }
}