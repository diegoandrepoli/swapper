package com.swapper.beans;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public final class PasswordEncoder {

    public String encode(String value) {
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        return enc.encode(value);
    }
}