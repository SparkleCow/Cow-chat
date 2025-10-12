package com.sparklecow.cowchat.message;

import com.sparklecow.cowchat.chat.Chat;
import com.sparklecow.cowchat.chat.ChatRepository;
import com.sparklecow.cowchat.user.User;
import com.sparklecow.cowchat.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public MessageResponseDto sendMessage(MessageRequestDto messageRequestDto) {

        Chat chat = chatRepository.findById(messageRequestDto.chatId())
                .orElseThrow(() -> new RuntimeException("Chat not found"));
        User sender = userRepository.findById(messageRequestDto.senderId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Message message = new Message();
        message.setChat(chat);
        message.setSender(sender);
        message.setContent(messageRequestDto.content());
        message.setTimestamp(LocalDateTime.now());

        return messageMapper.toDto(messageRepository.save(message));
    }

    public Message sendMessageWithoutUserOrChat(String content) {
        Message message = new Message();
        message.setMessageType(MessageType.TEXT);
        message.setId(UUID.randomUUID().toString());
        message.setChat(null);
        message.setSender(null);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        return message;
    }
}