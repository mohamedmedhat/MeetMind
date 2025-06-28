package com.mohamed.auth_service.service.command;

import java.util.UUID;

import com.mohamed.auth_service.dto.response.UserResponseDto;
import com.mohamed.auth_service.dto.request.UserRequestDto;

public interface UserCommandService {
    UserResponseDto updateUser(UUID id, UserRequestDto req);

    void deleteUser(UUID id);

}
