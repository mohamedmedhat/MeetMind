package com.mohamed.auth_service.refreshToken;

import com.mohamed.auth_service.user.User;
import org.mapstruct.Mapper;

import java.util.Date;

@Mapper
public interface RefreshTokenMapper {
    public default RefreshToken toRefreshToken(String token, User user, Date expireDate) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(token);
        refreshToken.setUserId(user.getId());
        refreshToken.setExpiryDate(expireDate);
        return refreshToken;
    }
}
