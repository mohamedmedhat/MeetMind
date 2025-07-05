package com.mohamed.auth_service.user.service.command;

import java.util.UUID;

import com.mohamed.auth_service.user.User;
import com.mohamed.auth_service.user.UserMapper;
import com.mohamed.auth_service.user.UserRepository;
import org.springframework.stereotype.Service;

import com.mohamed.auth_service.dto.request.UserRequestDto;
import com.mohamed.auth_service.dto.response.UserResponseDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Flux<UserResponseDto> createUsers(UserRequestDto[] requestDtos) {
        User[] users = this.userMapper.toUsers(requestDtos);
        return this.userRepository.saveAll(users)
                .map(userMapper::toUsersResponseDto);
    }

    @Override
    public Mono<UserResponseDto> updateUser(UUID id, UserRequestDto req) {
        Mono<User> user = this.userRepository.findById(id);
        User updatedUser = this.userMapper.toUpdatedUser(user, req);
        return this.userRepository.save(updatedUser)
                .map(userMapper::toUserResponseDto);
    }

    @Override
    public Mono<Void> deleteUser(UUID id) {
        return this.userRepository.deleteById(id);
    }

    @Override
    public Mono<Void> deleteUsers(UUID[] id) {
        Flux<User> users = this.userRepository.findByIds(id);
        return this.userRepository.deleteAll(users);
    }

}
