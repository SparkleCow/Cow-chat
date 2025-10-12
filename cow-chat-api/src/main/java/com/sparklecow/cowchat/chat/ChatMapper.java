package com.sparklecow.cowchat.chat;

import com.sparklecow.cowchat.message.MessageMapper;
import com.sparklecow.cowchat.message.MessageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMapper {

    private final MessageMapper messageMapper;

    public ChatResponseDto toDto(Chat chat) {
        if (chat == null) {
            return null;
        }

        List<String> participantIds = chat.getParticipants() != null
                ? chat.getParticipants().stream()
                .map(user -> user.getId())
                .collect(Collectors.toList())
                : List.of();

        List<MessageResponseDto> messageDtos = chat.getMessages() != null
                ? chat.getMessages().stream()
                .map(messageMapper::toDto)
                .collect(Collectors.toList())
                : List.of();

        return new ChatResponseDto(
                chat.getId(),
                chat.getName(),
                participantIds,
                messageDtos
        );
    }
}
