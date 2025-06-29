package com.mohamed.auth_service.user.service.command;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mohamed.auth_service.dto.request.UserRequestDto;
import com.mohamed.auth_service.dto.response.UserResponseDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService{

    @Override
    public Flux<UserResponseDto> createUsers(UserRequestDto[] requestDtos) {
        return null;
    }

    @Override
    public Mono<UserResponseDto> updateUser(UUID id, UserRequestDto req) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public Mono<Void> deleteUser(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public Mono<Void> deleteUsers(UUID[] id) {
        return null;
    }

}
