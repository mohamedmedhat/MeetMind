package com.mohamed.auth_service.refreshToken;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends R2dbcRepository<RefreshToken, UUID> {
    Mono<RefreshToken> findByUserId(UUID id);
    Mono<Void> deleteByToken(String token);
}
