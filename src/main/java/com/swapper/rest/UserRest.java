package com.swapper.rest;

import com.swapper.dto.LoginRequestDTO;
import com.swapper.dto.LoginResponseDTO;
import com.swapper.dto.PasswordRequestDTO;
import com.swapper.dto.RegisterRequestDTO;
import com.swapper.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/api/v1/account")
public class UserRest {

    @Autowired
    private UserService userService;

    @PostMapping(value = "register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequestDTO registerDTO) {
        try {
            userService.register(registerDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginDTO) {
        try {
            LoginResponseDTO authenticate = userService.authenticate(loginDTO);
            return ResponseEntity.ok(authenticate);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping(value = "password")
    public ResponseEntity<Void> password(@Valid @RequestBody PasswordRequestDTO passwordDTO) {
        try {
            userService.password(passwordDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}