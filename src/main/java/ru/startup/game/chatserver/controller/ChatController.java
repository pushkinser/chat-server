package ru.startup.game.chatserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import ru.startup.game.chatserver.model.Chat;
import ru.startup.game.chatserver.model.ChatMessage;
import ru.startup.game.chatserver.model.Message;
import ru.startup.game.chatserver.model.User;
import ru.startup.game.chatserver.service.ChatService;
import ru.startup.game.chatserver.service.MessageService;
import ru.startup.game.chatserver.service.UserService;

@Controller
@AllArgsConstructor
public class ChatController {

    private MessageService messageService;

    private ChatService chatService;

    private UserService userService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        Message message = new Message();
        Chat chat = chatService.findById(1L);
        message.setChat(chat);
        User user = userService.findUserByUserName(chatMessage.getSender());
        message.setUser(user);
        message.setMessage(chatMessage.getContent());
        messageService.save(message);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
