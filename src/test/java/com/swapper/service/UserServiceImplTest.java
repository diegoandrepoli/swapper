package com.swapper.service;

import com.swapper.assembler.UserAssembler;
import com.swapper.component.UserAuthenticationToken;
import com.swapper.dto.LoginRequestDTO;
import com.swapper.dto.RegisterRequestDTO;
import com.swapper.entities.User;
import com.swapper.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserServiceImpl.class})
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserAssembler userAssembler;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserAuthenticationToken userAuthenticationToken;

    @Mock
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

    @Mock
    private AuthenticationManager authenticationManager;

    private User user;

    private RegisterRequestDTO registerRequestDTO;

    private LoginRequestDTO loginRequestDTO;

    private static final String USERNAME = "myusername";

    private static final String EMAIL = "my@email.com";

    private static final String PASSWORD = "123456789";

    private static final String TOKEN = "8238R230230J20UJ230EU32J";

    @BeforeAll
    public void setup() {

        this.user = new User();
        user.setUsername(USERNAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setEnabled(true);

        this.registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setEmail(EMAIL);
        registerRequestDTO.setUsername(USERNAME);
        registerRequestDTO.setPassword(PASSWORD);

        this.loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUsername(USERNAME);
        loginRequestDTO.setPassword(PASSWORD);
    }

    @Test
    public void registerTest() throws Exception {
        when(passwordEncoder.encode(PASSWORD)).thenReturn(TOKEN);
        when(userAssembler.to(this.registerRequestDTO, TOKEN)).thenReturn(this.user);
        when(userRepository.save(this.user)).thenReturn(this.user);

        userService.register(this.registerRequestDTO);

        verify(userRepository).save(this.user);
    }

    @Test
    public void authenticateTest() throws Exception {
        when(userAuthenticationToken.getToken(any(), any())).thenReturn(usernamePasswordAuthenticationToken);
        when(userRepository.findBy(USERNAME)).thenReturn(this.user);
        when(jwtService.generateToken(EMAIL)).thenReturn(TOKEN);

        userService.authenticate(loginRequestDTO);

        verify(authenticationManager).authenticate(any());
    }
}