package com.mohamed.auth_service.user.service.auth;

import com.mohamed.auth_service.dto.request.LoginRequestDto;
import com.mohamed.auth_service.dto.request.UserRequestDto;
import com.mohamed.auth_service.dto.response.LoginResponseDto;
import com.mohamed.auth_service.dto.response.UserResponseDto;
import com.mohamed.auth_service.refreshToken.dto.response.RefreshTokenResponseDto;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<UserResponseDto> register(UserRequestDto req);

    Mono<LoginResponseDto> login(LoginRequestDto req, ServerHttpResponse response);

    Mono<RefreshTokenResponseDto> refreshToken(String refreshToken, ServerHttpResponse response);

    Mono<Void> logout(String token, ServerHttpResponse response);
}
