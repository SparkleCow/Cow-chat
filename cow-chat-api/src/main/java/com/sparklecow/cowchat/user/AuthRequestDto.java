package com.sparklecow.cowchat.user;

public record AuthRequestDto(
        String username,
        String password
) {
}
