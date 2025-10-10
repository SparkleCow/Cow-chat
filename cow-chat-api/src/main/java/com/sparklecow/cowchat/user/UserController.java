package com.sparklecow.cowchat.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /*@GetMapping("/self")
    public ResponseEntity<UserResponseDto> findLoggedUsed(Authentication authentication){
    }*/

    /*
    * TODO
    *  return the logged user
    *  update user information (self)
    *  delete user account (self)
    */
}
