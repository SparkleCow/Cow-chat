package com.sparklecow.cowchat.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> findAllUsersExceptSelf(Authentication authentication){
        return ResponseEntity.ok(userService.findAllUsersExceptSelf(authentication));
    }

    @GetMapping("/self")
    public ResponseEntity<UserResponseDto> findLoggedUsed(Authentication authentication){
        return ResponseEntity.ok(userService.findUserLogged(authentication));
    }

    @PutMapping("/image")
    public ResponseEntity<?> updateProfileImage(
            Authentication authentication,
            @RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = userService.updateProfileImage(authentication, file);
            return ResponseEntity.ok().body(imageUrl);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /*
    * TODO
    *  update user information (self)
    *  delete user account (self)
    */
}
