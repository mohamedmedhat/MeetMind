package com.mohamed.auth_service.service.auth;

import com.mohamed.auth_service.dto.request.LoginRequestDto;
import com.mohamed.auth_service.dto.request.UserRequestDto;
import com.mohamed.auth_service.dto.response.LoginResponseDto;
import com.mohamed.auth_service.dto.response.UserResponseDto;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<UserResponseDto> register(UserRequestDto req);

    Mono<LoginResponseDto> login(LoginRequestDto req);

    Mono<String> refreshToken(String token);

    Mono<Void> logout(String token);
}
