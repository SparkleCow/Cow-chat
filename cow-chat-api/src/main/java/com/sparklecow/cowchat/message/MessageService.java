package com.sparklecow.cowchat.message;

import com.sparklecow.cowchat.chat.Chat;
import com.sparklecow.cowchat.chat.ChatRepository;
import com.sparklecow.cowchat.user.User;
import com.sparklecow.cowchat.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public Message sendMessage(MessageRequest messageRequest) {

        Chat chat = chatRepository.findById(messageRequest.chatId())
                .orElseThrow(() -> new RuntimeException("Chat not found"));
        User sender = userRepository.findById(messageRequest.senderId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Message message = new Message();
        message.setChat(chat);
        message.setSender(sender);
        message.setContent(messageRequest.content());
        message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
    }

    public Message sendMessageWithoutUserOrChat(String content) {
        Message message = new Message();
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }
}