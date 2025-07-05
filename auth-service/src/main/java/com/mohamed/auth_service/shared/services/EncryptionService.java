package com.mohamed.auth_service.shared.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EncryptionService {
    private final PasswordEncoder passwordEncoder;


    public String encryptPassword(String password) {
        return this.passwordEncoder.encode(password);
    }

    public Boolean comparePassword(String rawPass, String pass) {
        return this.passwordEncoder.matches(rawPass, pass);
    }
}
