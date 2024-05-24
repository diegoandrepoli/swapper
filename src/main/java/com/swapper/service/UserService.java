package com.swapper.service;

import com.swapper.dto.LoginDTO;
import com.swapper.dto.PasswordDTO;
import com.swapper.entities.User;

public interface UserService {
    public void register(User user);

    public void login(LoginDTO loginDTO);

    public void password(PasswordDTO passwordDTO);
}