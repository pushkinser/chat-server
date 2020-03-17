package ru.startup.game.chatserver.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.dto.ChatMessageDto;
import ru.startup.game.chatserver.model.dto.MessageDto;
import ru.startup.game.chatserver.model.dto.UserDto;
import ru.startup.game.chatserver.model.entity.Chat;
import ru.startup.game.chatserver.model.entity.Label;
import ru.startup.game.chatserver.model.entity.Message;
import ru.startup.game.chatserver.model.mapper.MessageMapper;
import ru.startup.game.chatserver.repository.MessageRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageService {

    private MessageRepository messageRepository;

    private MessageMapper messageMapper;

    private ChatService chatService;

    private UserService userService;

    public ChatMessageDto save(ChatMessageDto chatMessageDto) {
        MessageDto messageDto = new MessageDto();
        UserDto user = userService.findUserByUserName(chatMessageDto.getSender());
        messageDto.setUser(user);
        messageDto.setMessage(chatMessageDto.getContent());

        Message message = messageMapper.messageDtoToMessage(messageDto);
        Chat chat = chatService.findModelById(1L);
        message.setChat(chat);
        Message saveMessage = messageRepository.save(message);

        return messageMapper.modelToChatMessageDto(saveMessage);
    }

    public void updateMessageLabel(Long messageId, Label findLabel) {
        Optional<Message> findMessage = messageRepository.findById(messageId);
        findMessage.ifPresent(message -> message.getLabels().add(findLabel));
    }
}
