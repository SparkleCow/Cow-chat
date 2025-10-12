package com.sparklecow.cowchat.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDto findUserLogged(Authentication authentication){
        return userMapper.toUserResponseDto((User) authentication.getPrincipal());
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException(""));
    }

    public List<UserResponseDto> findAllUsers(){
        return userRepository.findAll().stream().map(userMapper::toUserResponseDto).toList();
    }

    public List<UserResponseDto> findAllUsersExceptSelf(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return userRepository.findAllExcept(user.getId()).stream().map(userMapper::toUserResponseDto).toList();
    }
}
