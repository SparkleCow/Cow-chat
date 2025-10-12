package com.sparklecow.cowchat.ws;

import com.sparklecow.cowchat.message.Message;
import com.sparklecow.cowchat.message.MessageRequestDto;
import com.sparklecow.cowchat.message.MessageResponseDto;
import com.sparklecow.cowchat.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {

    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/{chatId}/send")
    public void sendMessage(@DestinationVariable String chatId, MessageRequestDto dto) {

        MessageResponseDto saved = messageService.sendMessage(dto);

        /*It sends the message at all users subscribed at chat topic*/
        messagingTemplate.convertAndSendToUser(
                saved.recipientIds().getFirst(),
                "/queue/messages",
                saved
        );

        messagingTemplate.convertAndSendToUser(
                saved.senderId(),
                "/queue/messages",
                saved
        );
    }
}
