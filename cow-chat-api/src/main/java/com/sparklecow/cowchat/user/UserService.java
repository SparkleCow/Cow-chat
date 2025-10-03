package com.sparklecow.cowchat.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException(""));
    }

    public List<UserResponseDto> findAllUsers(){
        return userRepository.findAll().stream().map(x -> userMapper.toUserResponseDto(x));
    }
}
