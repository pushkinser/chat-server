package ru.startup.game.chatserver.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.startup.game.chatserver.model.dto.*;
import ru.startup.game.chatserver.service.ChatService;
import ru.startup.game.chatserver.service.MessageService;
import ru.startup.game.chatserver.service.UserService;

import java.util.List;

import static ru.startup.game.chatserver.controller.util.AttributeUtil.SESSION_ATTRIBUTE_USERNAME;

@Log
@RestController
@AllArgsConstructor
public class ChatController {

    private MessageService messageService;

    private ChatService chatService;

    private UserService userService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageDto sendMessage(@Payload ChatMessageDto chatMessage) {
        return messageService.save(chatMessage);
    }

    @GetMapping("chat/history")
    public List<ChatMessageDto> getHistoryMessage(){
        log.info("Send history messages to user");
        return chatService.getChatHistory();
    }

    @PostMapping("chats")
    public List<ChatDto> getChats(@RequestBody UserDto user){
        UserDto userDto = userService.findUserByUserName(user.getUserName());
        return chatService.findByUser(userDto);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessageDto addUser(@Payload ChatMessageDto chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put(SESSION_ATTRIBUTE_USERNAME, chatMessage.getSender());
        return chatMessage;
    }
}
