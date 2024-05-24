package com.swapper.rest;

import com.swapper.assembler.UserAssembler;
import com.swapper.dto.LoginDTO;
import com.swapper.dto.PasswordDTO;
import com.swapper.dto.RegisterDTO;
import com.swapper.entities.User;
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

    @Autowired
    private UserAssembler userAssembler;

    @PostMapping(value = "register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterDTO registerDTO) {
        try {
            User user = userAssembler.to(registerDTO);
            userService.register(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            userService.login(loginDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "password")
    public ResponseEntity<Void> password(@Valid @RequestBody PasswordDTO passwordDTO) {
        try {
            userService.password(passwordDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}