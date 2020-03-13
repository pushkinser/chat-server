package ru.startup.game.chatserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.startup.game.chatserver.model.ChatMessage;
import ru.startup.game.chatserver.model.dto.ChatDto;
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
        MessageDto saveMessage = messageService.save(message);
        chatMessage.setId(saveMessage.getId());
        return chatMessage;
    }

    @GetMapping("chat/history")
    public List<ChatMessage> getHistoryMessage(){
        List<ChatMessage> chatMessages;
        List<MessageDto> messages = chatService.findById(1L).getMessages();
        chatMessages = messages.stream()
                .map(messageDto -> new ChatMessage(messageDto.getId(), ChatMessage.MessageType.CHAT, messageDto.getMessage(), messageDto.getUser().getUserName(),messageDto.getLabels()))
                .collect(Collectors.toList());
        return chatMessages;
    }

    @PostMapping("label")
    public void saveLabel (@Payload ChatMessage chatMessage){
        chatMessage
    }

    @PostMapping("chats")
    public List<ChatDto> getChats(@RequestBody UserDto user){
        UserDto userDto = userService.findUserByUserName(user.getUserName());
        List<ChatDto> chatsByUserName = chatService.findByUser(userDto);
        return chatsByUserName;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
