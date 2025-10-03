package com.sparklecow.cowchat.chat;

import com.sparklecow.cowchat.user.User;
import com.sparklecow.cowchat.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public Chat existOrcreateChat(String senderId, String recipientId){
        Optional<Chat> existingChat = chatRepository.findChatBetweenUsers(senderId, recipientId);

        if (existingChat.isPresent()) {
            return existingChat.get();
        }

        User sender = userRepository.findById(senderId).orElseThrow(() -> new RuntimeException(""));
        User recipient = userRepository.findById(recipientId).orElseThrow(() -> new RuntimeException(""));

        List<User> users = List.of(sender, recipient);

        return chatRepository.save(Chat.builder().name("").participants(users).build());
    }
}
