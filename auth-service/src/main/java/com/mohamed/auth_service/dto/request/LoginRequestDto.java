package com.mohamed.auth_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(
    @NotBlank
    @Email
    String email,

    @NotBlank
    @Size(min = 6, max = 40)
    String password
) {

}
