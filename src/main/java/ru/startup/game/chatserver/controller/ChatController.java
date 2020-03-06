package ru.startup.game.chatserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.startup.game.chatserver.model.ChatMessage;
import ru.startup.game.chatserver.model.dto.ChatDto;
import ru.startup.game.chatserver.model.dto.UserDto;
import ru.startup.game.chatserver.model.entity.Message;
import ru.startup.game.chatserver.service.ChatService;
import ru.startup.game.chatserver.service.MessageService;
import ru.startup.game.chatserver.service.UserService;

@RestController
@AllArgsConstructor
public class ChatController {

    private MessageService messageService;

    private ChatService chatService;

    private UserService userService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
  /*      Message message = new Message();
        ChatDto chat = chatService.findById(1L);
       message.setChat(chat);
        UserDto user = userService.findUserByUserName(chatMessage.getSender());
        message.setUser(user);
        message.setMessage(chatMessage.getContent());
        messageService.save(message);*/
        return chatMessage;
    }

    @GetMapping("chat/history")
    public ChatDto getHistoryMessage(){
//        ChatMessage chatMessage1 = new ChatMessage();
//        chatMessage1.setContent("123");
//        chatMessage1.setSender("history1");
//        chatMessage1.setType(ChatMessage.MessageType.CHAT);
//
//        ChatMessage chatMessage2 = new ChatMessage();
//        chatMessage2.setContent("234");
//        chatMessage2.setSender("history2");
//        chatMessage2.setType(ChatMessage.MessageType.CHAT);
          System.out.println(chatService.findById(1L));
        return chatService.findById(1L);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
