package com.swapper.repository;

import com.swapper.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void finbyIdTest() {
        Long id = 1L;
        String username = "Faking";
        String password = "mysecretpass123";
        String email = "faking@mail.com";
        boolean enabled = true;

        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setEnabled(enabled);

        userRepository.save(user);

        Optional<User> result = userRepository.findBy(1L, true);
        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
        assertEquals(password, result.get().getPassword());
        assertEquals(email, result.get().getEmail());
        assertEquals(enabled, result.get().isEnabled());
    }

    @Test
    void saveUsernameTest() {
        Long id = 1L;
        String username = "Jonatam";
        String password = "supersecret123";
        String email = "jonatam@Jonatam.com";
        boolean enabled = true;

        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setEnabled(enabled);

        userRepository.save(user);

        User result = userRepository.findBy(username, true);
        assertEquals(username, result.getUsername());
        assertEquals(password, result.getPassword());
        assertEquals(email, result.getEmail());
        assertEquals(enabled, result.isEnabled());
    }
}