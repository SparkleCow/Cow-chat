package com.sparklecow.cowchat.user;

public record UserRequestDto(
        String username,
        String password,
        String email
) {
}