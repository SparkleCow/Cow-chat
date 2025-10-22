package com.sparklecow.cowchat.user;

import com.sparklecow.cowchat.aws.S3Service;
import com.sparklecow.cowchat.common.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private S3Service s3Service;

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

    public String updateProfileImage(Authentication authentication, String imagePath) {
        if(imagePath == null || imagePath.isEmpty()){
            return "Profile imaged could not be updated";
        }
        User user = (User) authentication.getPrincipal();
        user.setImagePath(imagePath);
        userRepository.save(user);
        return "Profile imaged updated successfully";
    }
}
