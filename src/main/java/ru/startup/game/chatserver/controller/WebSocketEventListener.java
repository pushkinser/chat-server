package ru.startup.game.chatserver.controller;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import ru.startup.game.chatserver.model.dto.ChatMessageDto;
import ru.startup.game.chatserver.service.ChatService;

@Component
@AllArgsConstructor
public class WebSocketEventListener {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    private final SimpMessageSendingOperations messagingTemplate;

    private ChatService chatService;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketListener(SessionSubscribeEvent event) {
        logger.info("Subscribe");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {

            ChatMessageDto chatMessageDto = new ChatMessageDto();
            chatMessageDto.setContent("f,d");
            chatMessageDto.setType(ChatMessageDto.MessageType.CHAT);
            chatMessageDto.setSender(username);

            messagingTemplate.convertAndSendToUser(username,"/topic/public", chatMessageDto);
            logger.info("Send history messages to new user");
        }


    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            logger.info("User Disconnected : " + username);

            ChatMessageDto chatMessageDto = new ChatMessageDto();
            chatMessageDto.setType(ChatMessageDto.MessageType.LEAVE);
            chatMessageDto.setSender(username);

            messagingTemplate.convertAndSend("/topic/public", chatMessageDto);
        }
    }
}
