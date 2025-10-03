package com.sparklecow.cowchat.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User toUser(UserRequestDto userRequestDto) {
        return User
                .builder()
                .email(userRequestDto.email())
                .password(passwordEncoder.encode(userRequestDto.password()))
                .username(userRequestDto.username())
                .build();
    }
}
