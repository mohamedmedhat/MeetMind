package com.mohamed.auth_service.service.query;

import com.mohamed.auth_service.dto.response.UserResponseDto;
import java.util.UUID;

public interface UserQueryService {
    UserResponseDto getUser(UUID id);

    UserResponseDto[] getAllUsers(int page, int size);

    UserResponseDto getUserByEmail(String email);
}
