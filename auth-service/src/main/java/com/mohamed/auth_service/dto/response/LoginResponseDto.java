package com.mohamed.auth_service.dto.response;

public record LoginResponseDto(
    UserResponseDto user,
    String accessToken,
    String refreshToken
) {

}
