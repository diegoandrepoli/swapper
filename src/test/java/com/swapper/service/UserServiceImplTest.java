package com.swapper.service;

import com.swapper.entities.User;
import com.swapper.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void registerTest() {
        User user = new User();
        user.setName("My name");
        user.setEmail("my@email.com");
        user.setPassword("123456789");
        user.setEnabled(true);

        when(userRepository.save(user)).thenReturn(user);
        userService.register(user);

        verify(passwordEncoder, timeout(2)).encode("123456789");
    }
}