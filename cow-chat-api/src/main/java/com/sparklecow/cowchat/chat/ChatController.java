package com.sparklecow.cowchat.chat;

import com.sparklecow.cowchat.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping()
    public ResponseEntity<ChatResponseDto> findOrCreateChat(
            @RequestBody ChatRequestDto chatRequestDto,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(chatService.existOrcreatePrivateChat(user.getId(), chatRequestDto.id()));
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatResponseDto> findOrCreateGroupChat(
            @RequestBody ChatRequestDto chatRequestDto,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(chatService.existOrcreatePrivateChat(user.getId(), chatRequestDto.id()));
    }
}
