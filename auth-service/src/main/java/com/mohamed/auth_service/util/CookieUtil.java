package com.mohamed.auth_service.util;

import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.time.Duration;

@Component
public class CookieUtil {
    public static final String REFRESH_TOKEN = "refreshToken";

    public void setInCookie(String token, ServerHttpResponse response) {
        ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN, token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(Duration.ofDays(7))
                .sameSite("Strict")
                .build();
        response.addCookie(cookie);
    }

    public String getRefreshTokenFromCookie(ServerHttpRequest request) {
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        HttpCookie cookie = cookies.getFirst(REFRESH_TOKEN);
        return cookie != null ? cookie.getValue() : null;
    }


    public void clearRefreshTokenCookie(ServerHttpResponse response) {
        ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN, "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(Duration.ZERO)
                .build();
        response.addCookie(cookie);
    }
}
