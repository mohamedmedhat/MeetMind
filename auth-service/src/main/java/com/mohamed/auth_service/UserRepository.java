package com.mohamed.auth_service;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<User, UUID> {

    @Query("SELECT * FROM users WHERE email = :email")
    Mono<User> findByEmail(@Param("email") String email);

    @Query("SELECT * FROM users WHERE id = ANY(:ids)")
    Flux<User> findByIds(@Param("ids") UUID[] ids);

    @Query("SELECT * FROM users LIMIT :limit OFFSET :offset")
    Flux<User> findAllPaged(@Param("limit") int limit, @Param("offset") int offset);
}