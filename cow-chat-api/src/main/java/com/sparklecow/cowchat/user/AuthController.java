package com.sparklecow.cowchat.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody UserRequestDto userRequestDto){
        User user = authService.createUser(userRequestDto);
        URI uri = URI.create("/users/" + user.getId());
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> loginUser(@RequestBody AuthRequestDto authRequestDto){
        return ResponseEntity.ok(authService.login(authRequestDto));
    }
}
