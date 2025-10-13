package com.sparklecow.cowchat.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequestDto(
        @NotBlank
        String username,
        @Size(min = 5)
        String password
) {
}
