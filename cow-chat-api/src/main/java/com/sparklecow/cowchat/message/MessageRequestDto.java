package com.sparklecow.cowchat.message;

public record MessageRequestDto(
        String chatId,
        String senderId,
        String content
) {
}
