package com.mohamed.auth_service.service.query;

import com.mohamed.auth_service.dto.response.UserResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserQueryService {
    Mono<UserResponseDto> getUser(UUID id);

    Flux<UserResponseDto> getUsersByIds(UUID[] id);

    Flux<UserResponseDto> getAllUsers(int page, int size);

    Mono<UserResponseDto> getUserByEmail(String email);
}
