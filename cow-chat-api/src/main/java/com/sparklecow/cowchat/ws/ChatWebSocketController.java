package com.sparklecow.cowchat.ws;

import com.sparklecow.cowchat.message.Message;
import com.sparklecow.cowchat.message.MessageRequest;
import com.sparklecow.cowchat.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {

    private final MessageService messageService;

    @MessageMapping("/chat.send")
    @SendTo("/topic/messages")
    public Message send(MessageRequest messageRequest) {
        return messageService.sendMessageWithoutUserOrChat(messageRequest.content());
    }

    @MessageMapping("/chat.{chatId}.send")
    @SendTo("/topic/chat.{chatId}")
    public Message sendToChat(@DestinationVariable String chatId, MessageRequest messageRequest) {
        return messageService.sendMessageWithoutUserOrChat(messageRequest.content());
    }
}
