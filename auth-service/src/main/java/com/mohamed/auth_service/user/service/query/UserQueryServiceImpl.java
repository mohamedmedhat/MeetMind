package com.mohamed.auth_service.user.service.query;

import java.util.UUID;

import com.mohamed.auth_service.user.UserMapper;
import com.mohamed.auth_service.user.UserRepository;
import com.mohamed.auth_service.exception.UserNotFoundException;

import java.util.Arrays;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mohamed.auth_service.dto.response.UserResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Cacheable(value = "getUser", key = "get User " + "#id")
    @Transactional(readOnly = true)
    @Override
    public Mono<UserResponseDto> getUser(UUID id) {
        return this.userRepository.findById(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException("user with id: " + id + " not found")))
                .map(userMapper::toUserResponseDto);
    }

    @Cacheable(value = "getUserByIds", key = "get Users " + "#id")
    @Transactional(readOnly = true)
    @Override
    public Flux<UserResponseDto> getUsersByIds(UUID[] id) {
        return this.userRepository.findByIds(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException("failed to find users with ids " + Arrays.toString(id))))
                .map(userMapper::toUserResponseDto);
    }

    @Cacheable(value = "getAllUsers", key = "get Users " + "#page" + "#size")
    @Transactional(readOnly = true)
    @Override
    public Flux<UserResponseDto> getAllUsers(int page, int size) {
        int offset = page * size;
        return this.userRepository.findAllPaged(size, offset)
                .switchIfEmpty(Mono.error(new UserNotFoundException("users not found")))
                .map(userMapper::toUserResponseDto);
    }

    @Cacheable(value = "getUseByEmail", key = "get User " + "#email")
    @Transactional(readOnly = true)
    @Override
    public Mono<UserResponseDto> getUserByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new UserNotFoundException("user with email " + email + " not found")))
                .map(userMapper::toUserResponseDto);
    }

}
