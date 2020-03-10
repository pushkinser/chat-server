package ru.startup.game.chatserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.startup.game.chatserver.model.ChatMessage;
import ru.startup.game.chatserver.model.dto.MessageDto;
import ru.startup.game.chatserver.model.dto.UserDto;
import ru.startup.game.chatserver.service.ChatService;
import ru.startup.game.chatserver.service.MessageService;
import ru.startup.game.chatserver.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ChatController {

    private MessageService messageService;

    private ChatService chatService;

    private UserService userService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        MessageDto message = new MessageDto();
        UserDto user = userService.findUserByUserName(chatMessage.getSender());
        message.setUser(user);
        message.setMessage(chatMessage.getContent());
        messageService.save(message);
        return chatMessage;
    }

    @GetMapping("chat/history")
    public List<ChatMessage> getHistoryMessage(){
//        ChatMessage chatMessage1 = new ChatMessage();
//        chatMessage1.setContent("123");
//        chatMessage1.setSender("history1");
//        chatMessage1.setType(ChatMessage.MessageType.CHAT);
//
//        ChatMessage chatMessage2 = new ChatMessage();
//        chatMessage2.setContent("234");
//        chatMessage2.setSender("history2");
//        chatMessage2.setType(ChatMessage.MessageType.CHAT);
        List<ChatMessage> chatMessages;
        List<MessageDto> messages = chatService.findById(1L).getMessages();
        chatMessages = messages.stream()
                .map(messageDto -> new ChatMessage(ChatMessage.MessageType.CHAT, messageDto.getMessage(), messageDto.getUser().getUserName()))
                .collect(Collectors.toList());
        return chatMessages;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
