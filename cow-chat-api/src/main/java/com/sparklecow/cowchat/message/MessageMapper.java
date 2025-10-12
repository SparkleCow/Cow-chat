package com.sparklecow.cowchat.message;

import com.sparklecow.cowchat.user.User;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MessageMapper {

    public MessageResponseDto toDto(Message message) {
        if (message == null) return null;

        return new MessageResponseDto(
                message.getId(),
                message.getContent(),
                message.getMessageType(),
                message.getFilePath(),
                message.getChat().getId(),
                message.getSender() != null ? message.getSender().getId() : null,
                message.getRecipients() != null
                        ? message.getRecipients().stream()
                        .map(User::getId)
                        .collect(Collectors.toList())
                        : null,
                message.getTimestamp(),
                message.isDelivered(),
                message.isRead()
        );
    }
}
