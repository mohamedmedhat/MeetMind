package com.mohamed.auth_service.user;

import com.mohamed.auth_service.dto.request.UserRequestDto;
import com.mohamed.auth_service.dto.response.LoginResponseDto;
import com.mohamed.auth_service.dto.response.UserResponseDto;
import org.mapstruct.Mapper;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public default User toUser(UserRequestDto requestDto, String encryptedPass){
        return new User(
                UUID.randomUUID(),
                requestDto.name(),
                requestDto.email(),
                encryptedPass,
                requestDto.roles(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public default User[] toUsers(UserRequestDto[] requestDtos) {
        return Arrays.stream(requestDtos)
                .map(req -> new User(
                        UUID.randomUUID(),
                        req.name(),
                        req.email(),
                        req.password(),
                        req.roles(),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                ))
                .toArray(User[]::new);
    }
    UserResponseDto toUserResponseDto(User user);
    UserResponseDto toUsersResponseDto(User[] user);
    User toUpdatedUser(Mono<User> user, UserRequestDto req);
    LoginResponseDto toLoginResponseDto(User user,String token, String refreshToken);
}
