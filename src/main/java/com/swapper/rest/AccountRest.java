package com.swapper.rest;

import com.swapper.dto.LoginDTO;
import com.swapper.dto.PasswordDTO;
import com.swapper.dto.RegisterDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/api/v1/account")
public class AccountRest {

    @PostMapping(value = "register")
    public void register(@Valid @RequestBody RegisterDTO registerDTO) {
        //Implement here!
    }

    @PostMapping(value = "login")
    public void login(@Valid @RequestBody LoginDTO loginDTO) {
        //Implement here!
    }

    @PostMapping(value = "password")
    public void password(@Valid @RequestBody PasswordDTO passwordDTO) {
        //Implement here!
    }
}
