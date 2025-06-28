package com.mohamed.auth_service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohamed.auth_service.dto.request.LoginRequestDto;
import com.mohamed.auth_service.dto.request.UserRequestDto;
import com.mohamed.auth_service.dto.response.LoginResponseDto;
import com.mohamed.auth_service.dto.response.UserResponseDto;
import com.mohamed.auth_service.service.auth.AuthService;
import com.mohamed.auth_service.service.command.UserCommandService;
import com.mohamed.auth_service.service.query.UserQueryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @PostMapping("/auth/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRequestDto req){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.authService.register(req));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto req){
        return ResponseEntity.status(HttpStatus.OK).body(this.authService.login(req));
    }

    @PostMapping("/auth/refresh-token")
    public ResponseEntity<String> refreshToken(@Valid @NotBlank String token){
        return ResponseEntity.ok(this.authService.refreshToken(token));
    }



}
