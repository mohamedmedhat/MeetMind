package com.mohamed.auth_service.user.service.auth;

import org.springframework.stereotype.Service;

import com.mohamed.auth_service.dto.request.LoginRequestDto;
import com.mohamed.auth_service.dto.request.UserRequestDto;
import com.mohamed.auth_service.dto.response.LoginResponseDto;
import com.mohamed.auth_service.dto.response.UserResponseDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Override
    public Mono<UserResponseDto> register(UserRequestDto req) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public Mono<LoginResponseDto> login(LoginRequestDto req) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public Mono<String> refreshToken(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refreshToken'");
    }

    @Override
    public Mono<Void> logout(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }

}
