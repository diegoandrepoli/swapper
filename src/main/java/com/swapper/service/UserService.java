package com.swapper.service;

import com.swapper.dto.LoginRequestDTO;
import com.swapper.dto.PasswordRequestDTO;
import com.swapper.dto.RegisterRequestDTO;
import com.swapper.dto.LoginResponseDTO;

public interface UserService {

    void register(RegisterRequestDTO dto) throws Exception;

    LoginResponseDTO authenticate(LoginRequestDTO dto) throws Exception;

    void password(PasswordRequestDTO dto) throws Exception;
}