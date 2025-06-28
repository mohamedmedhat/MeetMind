package com.mohamed.auth_service.dto.response;

import java.util.Set;
import java.util.UUID;

import com.mohamed.auth_service.shared.Roles;

public record UserResponseDto(
    UUID id,
    String name,
    String email,
    Set<Roles> roles
) {

}
