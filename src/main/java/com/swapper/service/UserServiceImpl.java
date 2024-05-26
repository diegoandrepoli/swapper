package com.swapper.service;

import com.swapper.assembler.UserAssembler;
import com.swapper.component.UserAuthenticationToken;
import com.swapper.dto.LoginRequestDTO;
import com.swapper.dto.PasswordRequestDTO;
import com.swapper.dto.RegisterRequestDTO;
import com.swapper.dto.LoginResponseDTO;
import com.swapper.entities.User;
import com.swapper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAssembler userAssembler;

    @Autowired
    private UserAuthenticationToken securityToken;

    @Override
    public void register(RegisterRequestDTO registerRequestDTO) throws Exception {
        String password = passwordEncoder.encode(registerRequestDTO.getPassword());
        User user = userAssembler.to(registerRequestDTO, password);

        userRepository.save(user);
    }

    public LoginResponseDTO authenticate(LoginRequestDTO dto) throws Exception {
        try {
            UsernamePasswordAuthenticationToken token = securityToken.getToken(dto.getUsername(), dto.getPassword());
            authenticationManager.authenticate(token);
        } catch (Exception e){
            throw new Exception("Invalid credentials", e);
        }

        final User user = userRepository.findBy(dto.getUsername());
        return new LoginResponseDTO(jwtService.generateToken(user.getUsername()));
    }

    @Override
    public void password(Long id, PasswordRequestDTO passwordRequestDTO) throws Exception {
        User user = userRepository.findBy(id).orElseThrow();
        boolean isValid = passwordEncoder.matches(passwordRequestDTO.getPassword(), user.getPassword());

        if(isValid) {
            String newPassword = passwordEncoder.encode(passwordRequestDTO.getNewPassword());
            user.setPassword(newPassword);
            userRepository.save(user);
        }
    }
}