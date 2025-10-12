package com.sparklecow.cowchat.chat;

import com.sparklecow.cowchat.message.MessageResponseDto;

import java.util.List;

public record ChatResponseDto(
        String id,
        String name,
        List<String> participantsId,
        List<MessageResponseDto> messages
){}
