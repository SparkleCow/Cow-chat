package com.sparklecow.cowchat.message;

import java.time.LocalDateTime;
import java.util.List;

public record MessageResponseDto(
        String id,
        String content,
        MessageType messageType,
        String filePath,
        String senderId,
        List<String> recipientIds,
        LocalDateTime timestamp,
        boolean delivered,
        boolean read
) {}