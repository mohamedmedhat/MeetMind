package com.mohamed.auth_service.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mohamed.auth_service.dto.request.LoginRequestDto;
import com.mohamed.auth_service.dto.request.UserRequestDto;
import com.mohamed.auth_service.dto.response.LoginResponseDto;
import com.mohamed.auth_service.dto.response.UserResponseDto;
import com.mohamed.auth_service.user.service.auth.AuthService;
import com.mohamed.auth_service.user.service.command.UserCommandService;
import com.mohamed.auth_service.user.service.query.UserQueryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @PostMapping("/auth/register")
    public Mono<ResponseEntity<UserResponseDto>> register(@Valid @RequestBody UserRequestDto req) {
        return this.authService.register(req)
                .map(res -> ResponseEntity.status(HttpStatus.CREATED).body(res));
    }

    @PostMapping("/auth/login")
    public Mono<ResponseEntity<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto req) {
        return this.authService.login(req)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/auth/refresh-token")
    public Mono<ResponseEntity<String>> refreshToken(@Valid @NotBlank String token) {
        return this.authService.refreshToken(token)
                .map(ResponseEntity::ok);
    }

    @PostMapping("auth/logout")
    public Mono<ResponseEntity<Void>> logout(@RequestParam("token") String token) {
        return this.authService.logout(token)
                .map(res -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public Flux<ResponseEntity<UserResponseDto>> createUsers(@Valid @RequestBody UserRequestDto[] requestDtos) {
        return this.userCommandService.createUsers(requestDtos)
                .map(res -> ResponseEntity.status(HttpStatus.CREATED).body(res));
    }


    @PutMapping("/{id}")
    public Mono<ResponseEntity<UserResponseDto>> updateUser(@PathVariable("id") UUID id, @Valid @RequestBody UserRequestDto req) {
        return this.userCommandService.updateUser(id, req)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("id") UUID id) {
        return this.userCommandService.deleteUser(id)
                .map(res -> ResponseEntity.noContent().build());
    }

    @DeleteMapping
    public Mono<ResponseEntity<Void>> deleteUsers(@RequestParam("ids") UUID[] ids) {
        return this.userCommandService.deleteUsers(ids)
                .map(res -> ResponseEntity.noContent().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserResponseDto>> getUser(@PathVariable("id") UUID id) {
        return this.userQueryService.getUser(id)
                .map(ResponseEntity::ok);
    }

    @GetMapping
    public Flux<ResponseEntity<UserResponseDto>> getUsersByIds(@RequestParam("ids") UUID[] ids) {
        return this.userQueryService.getUsersByIds(ids)
                .map(ResponseEntity::ok);
    }

    @GetMapping
    public Flux<ResponseEntity<UserResponseDto>> getAllUsers(@RequestParam("page") int page, @RequestParam("size") int size) {
        return this.userQueryService.getAllUsers(page, size)
                .map(ResponseEntity::ok);
    }


}