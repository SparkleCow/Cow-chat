package com.sparklecow.cowchat.message;

import java.util.List;

public record MessageRequestDto(
        String chatId,
        String senderId,
        String content,
        MessageType messageType,
        String filePath,
        List<String> recipientIds
) {}