package com.mohamed.auth_service.service.auth;

import com.mohamed.auth_service.dto.request.LoginRequestDto;
import com.mohamed.auth_service.dto.request.UserRequestDto;
import com.mohamed.auth_service.dto.response.LoginResponseDto;
import com.mohamed.auth_service.dto.response.UserResponseDto;

public interface AuthService {
    UserResponseDto register(UserRequestDto req);

    LoginResponseDto login(LoginRequestDto req);

    String refreshToken(String token);

    void logout(String token);
}
