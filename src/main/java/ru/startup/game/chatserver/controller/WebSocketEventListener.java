package ru.startup.game.chatserver.controller;


import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import ru.startup.game.chatserver.model.dto.ChatMessageDto;

import static ru.startup.game.chatserver.controller.util.AttributeUtil.*;

@Log
@Component
@AllArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get(SESSION_ATTRIBUTE_USERNAME);
        if (username != null) {
            log.info("User Disconnected : " + username);

            ChatMessageDto chatMessageDto = new ChatMessageDto();
            chatMessageDto.setType(ChatMessageDto.MessageType.LEAVE);
            chatMessageDto.setSender(username);

            messagingTemplate.convertAndSend("/topic/public", chatMessageDto);
        }
    }
}

