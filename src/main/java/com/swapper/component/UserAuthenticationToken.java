package com.swapper.component;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationToken {

    public UsernamePasswordAuthenticationToken getToken(String username, String password) {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}