package com.sparklecow.cowchat.user;

import java.time.LocalDateTime;

public record UserResponseDto(
        String id,
        String username,
        String email,
        String imagePath,
        LocalDateTime lastSeen,
        boolean isOnline
) {
}