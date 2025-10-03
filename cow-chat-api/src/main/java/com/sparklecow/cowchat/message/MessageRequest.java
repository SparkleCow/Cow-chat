package com.sparklecow.cowchat.message;

public record MessageRequest(
        String chatId,
        String senderId,
        String content
) {
}
