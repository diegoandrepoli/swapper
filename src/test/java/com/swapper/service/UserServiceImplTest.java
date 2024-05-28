package com.swapper.service;

import com.swapper.assembler.UserAssembler;
import com.swapper.component.UserAuthenticationToken;
import com.swapper.dto.LoginRequestDTO;
import com.swapper.dto.LoginResponseDTO;
import com.swapper.dto.RegisterRequestDTO;
import com.swapper.entities.User;
import com.swapper.repository.UserRepository;
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
    private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    public void registerTest() throws Exception {
        String token = "8238R230230J20UJ230EU32J";

        User user = new User();
        user.setUsername("myusername");
        user.setEmail("my@email.com");
        user.setPassword("123456789");
        user.setEnabled(true);

        RegisterRequestDTO dto  = new RegisterRequestDTO();
        dto.setEmail("my@email.com");
        dto.setUsername("myusername");
        dto.setPassword("123456789");

        when(this.passwordEncoder.encode("123456789")).thenReturn(token);
        when(this.userAssembler.to(dto, token)).thenReturn(user);
        when(this.userRepository.save(user)).thenReturn(user);

        this.userService.register(dto);

        verify(this.userRepository).save(user);
    }

    @Test
    public void authenticateTest() throws Exception {
        String token = "8238R230230J20UJ230EU32J";

        User user = new User();
        user.setUsername("myusername");
        user.setEmail("my@email.com");
        user.setPassword("123456789");
        user.setEnabled(true);

        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setUsername("myusername");
        dto.setPassword("123456789");

        when(this.userAuthenticationToken.getToken("myusername", "123456789")).thenReturn(this.usernamePasswordAuthenticationToken);
        when(this.userRepository.findBy("myusername", true)).thenReturn(user);
        when(this.jwtService.generateToken("myusername")).thenReturn(token);

        LoginResponseDTO response = this.userService.authenticate(dto);

        verify(this.authenticationManager).authenticate(any());
        assert(!response.getToken().isEmpty());
    }
}