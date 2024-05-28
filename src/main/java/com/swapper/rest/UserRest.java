package com.swapper.rest;

import com.swapper.dto.LoginRequestDTO;
import com.swapper.dto.LoginResponseDTO;
import com.swapper.dto.PasswordRequestDTO;
import com.swapper.dto.RegisterRequestDTO;
import com.swapper.entities.User;
import com.swapper.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/api/account")
@Tag(name = "Account")
public class UserRest {

    @Autowired
    private UserService userService;

    @PostMapping(value = "register")
    @Operation(summary = "This endpoint register new user.")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequestDTO registerDTO) {
        try {
            userService.register(registerDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping(value = "login")
    @Operation(summary = "This endpoint login with username and password.")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginDTO) {
        try {
            LoginResponseDTO loginResponseDTO = userService.authenticate(loginDTO);
            return ResponseEntity.ok(loginResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping(value = "password")
    @Operation(summary = "This endpoint update user password.")
    public ResponseEntity<Void> password(@AuthenticationPrincipal User user, @Valid @RequestBody PasswordRequestDTO passwordDTO) {
        try {
            userService.password(user.getId(), passwordDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}