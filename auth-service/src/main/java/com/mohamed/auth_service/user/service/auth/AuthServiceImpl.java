package com.mohamed.auth_service.user.service.auth;

import com.mohamed.auth_service.exception.BadCredentialsException;
import com.mohamed.auth_service.exception.InvalidTokenException;
import com.mohamed.auth_service.exception.UserNotFoundException;
import com.mohamed.auth_service.refreshToken.RefreshTokenService;
import com.mohamed.auth_service.refreshToken.dto.response.RefreshTokenResponseDto;
import com.mohamed.auth_service.shared.services.EncryptionService;
import com.mohamed.auth_service.user.User;
import com.mohamed.auth_service.user.UserMapper;
import com.mohamed.auth_service.user.UserRepository;
import com.mohamed.auth_service.user.service.query.UserQueryService;
import com.mohamed.auth_service.util.CookieUtil;
import com.mohamed.auth_service.util.JwtUtil;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.mohamed.auth_service.dto.request.LoginRequestDto;
import com.mohamed.auth_service.dto.request.UserRequestDto;
import com.mohamed.auth_service.dto.response.LoginResponseDto;
import com.mohamed.auth_service.dto.response.UserResponseDto;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final EncryptionService encryptionService;
    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;

    @Override
    public Mono<UserResponseDto> register(UserRequestDto req) {
        String encodedPass = this.encryptionService.encryptPassword(req.password());
        User user = this.userMapper.toUser(req, encodedPass);
        return this.userRepository.save(user)
                .map(userMapper::toUserResponseDto);
    }

    @Override
    public Mono<LoginResponseDto> login(LoginRequestDto req, ServerHttpResponse response) {
        return authenticate(req)
                .flatMap(a -> handleLoginSuccess(a, response));
    }

    @Override
    public Mono<Void> logout(String refreshToken, ServerHttpResponse response) {
        return Mono.justOrEmpty(refreshToken)
                .flatMap(refreshTokenService::deleteByToken)
                .doOnTerminate(() -> cookieUtil.clearRefreshTokenCookie(response))
                .then();
    }

    @Override
    public Mono<RefreshTokenResponseDto> refreshToken(String refreshToken, ServerHttpResponse response) {
        return Mono.justOrEmpty(refreshToken)
                .switchIfEmpty(Mono.error(new InvalidTokenException("Refresh token not found")))
                .flatMap(token -> {
                    if (!jwtUtil.validateToken(token)) {
                        return Mono.error(new InvalidTokenException("Refresh token is invalid or expired"));
                    }

                    String email = jwtUtil.extractEmail(token);
                    return userRepository.findByEmail(email)
                            .flatMap(user -> {
                                String newAccessToken = jwtUtil.generateToken(user);
                                String newRefreshToken = jwtUtil.generateRefreshToken(user);

                                return refreshTokenService.saveOrUpdateRefreshToken(user, newRefreshToken)
                                        .doOnSuccess(ignored -> cookieUtil.setInCookie(newRefreshToken, response))
                                        .thenReturn(new RefreshTokenResponseDto(newAccessToken, newRefreshToken));
                            });
                });
    }


    private Mono<User> authenticate(LoginRequestDto req) {
        return Mono.fromCallable(() -> authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(req.email(), req.password())
                ))
                .then(userRepository.findByEmail(req.email()))
                .switchIfEmpty(Mono.error(new UserNotFoundException("User not found")))
                .flatMap(user -> encryptionService.comparePassword(user.getPassword(), req.password())
                        ? Mono.just(user)
                        : Mono.error(new BadCredentialsException("Invalid credentials"))
                );
    }

    private Mono<LoginResponseDto> handleLoginSuccess(User user, ServerHttpResponse res) {
        String token = jwtUtil.generateToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);

        return refreshTokenService.saveOrUpdateRefreshToken(user, refreshToken)
                .then(Mono.defer(() -> {
                    this.cookieUtil.setInCookie(refreshToken, res);
                    return Mono.just(userMapper.toLoginResponseDto(user, token, refreshToken));
                }));
    }
}
