package com.mohamed.auth_service.user;

import com.mohamed.auth_service.dto.request.UserRequestDto;
import com.mohamed.auth_service.dto.response.LoginResponseDto;
import com.mohamed.auth_service.dto.response.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequestDto requestDto);
    UserResponseDto toUserResponseDto(User user);
    LoginResponseDto toLoginResponseDto(User user,String token, String refreshToken);
}
