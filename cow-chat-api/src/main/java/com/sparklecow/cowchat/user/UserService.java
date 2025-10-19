package com.sparklecow.cowchat.user;

import com.sparklecow.cowchat.common.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final FileService fileService;

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

    public String updateProfileImage(Authentication authentication, MultipartFile file) {
        User user = (User) authentication.getPrincipal();
        try {
            /*Process file and upload at s3*/
            String imageUrl = fileService.processAndUpload(file, "users/" + user.getId());

            user.setImagePath(imageUrl);
            userRepository.save(user);

            return imageUrl;
        } catch (Exception e) {
            throw new RuntimeException("Error updating profile image: " + e.getMessage(), e);
        }
    }
}
