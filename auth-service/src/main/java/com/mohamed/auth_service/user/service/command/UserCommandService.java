package com.mohamed.auth_service.user.service.command;

import java.util.UUID;

import com.mohamed.auth_service.dto.response.UserResponseDto;
import com.mohamed.auth_service.dto.request.UserRequestDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserCommandService {
    Flux<UserResponseDto> createUsers(UserRequestDto[] requestDtos);

    Mono<UserResponseDto> updateUser(UUID id, UserRequestDto req);

    Mono<Void> deleteUser(UUID id);

    Mono<Void> deleteUsers(UUID[] id);

}
