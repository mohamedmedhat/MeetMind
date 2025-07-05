package com.mohamed.auth_service.dto.request;

import com.mohamed.auth_service.shared.enums.Roles;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

import java.util.Set;

public record UserRequestDto(
    @NotBlank
    @Size(min = 2, max = 40)
    String name,

    @NotBlank
    @Email
    String email,
    
    @NotBlank
    @Size(min = 6, max = 40)
    String password,

    Set<Roles> roles
) {

}
