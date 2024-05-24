package com.swapper.service;

import com.swapper.beans.PasswordEncoder;
import com.swapper.dto.LoginDTO;
import com.swapper.dto.PasswordDTO;
import com.swapper.entities.User;
import com.swapper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setEnabled(true);

        userRepository.save(user);
    }

    @Override
    public void login(LoginDTO loginDTO) {
        // implement here!
    }

    @Override
    public void password(PasswordDTO passwordDTO) {
        // implement here!
    }
}