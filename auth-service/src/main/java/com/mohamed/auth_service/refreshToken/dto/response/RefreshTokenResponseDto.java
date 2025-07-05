package com.mohamed.auth_service.refreshToken.dto.response;

public record RefreshTokenResponseDto(
        String newAccessToken,
        String newRefreshToken
) {
}
