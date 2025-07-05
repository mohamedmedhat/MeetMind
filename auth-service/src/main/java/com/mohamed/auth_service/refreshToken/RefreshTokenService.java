package com.mohamed.auth_service.refreshToken;

import com.mohamed.auth_service.user.User;
import com.mohamed.auth_service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenMapper refreshTokenMapper;
    private final JwtUtil jwtUtil;

    public Mono<Void> saveOrUpdateRefreshToken(User user, String refreshToken) {
        Date expiryDate = jwtUtil.extractExpiration(refreshToken);

        return refreshTokenRepository.findByUserId(user.getId())
                .flatMap(existing -> {
                    existing.setToken(refreshToken);
                    existing.setExpiryDate(expiryDate);
                    return refreshTokenRepository.save(existing).then();
                })
                .switchIfEmpty(
                        Mono.defer(() -> {
                            RefreshToken newToken = refreshTokenMapper.toRefreshToken(refreshToken, user, expiryDate);
                            return refreshTokenRepository.save(newToken).then();
                        })
                );
    }

    public Mono<Void> deleteByToken(String token) {
        return refreshTokenRepository.deleteByToken(token);
    }
}

