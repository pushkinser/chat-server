package ru.startup.game.chatserver.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.dto.MessageDto;
import ru.startup.game.chatserver.model.entity.Chat;
import ru.startup.game.chatserver.model.entity.Label;
import ru.startup.game.chatserver.model.entity.Message;
import ru.startup.game.chatserver.model.mapper.MessageMapper;
import ru.startup.game.chatserver.repository.MessageRepository;
import ru.startup.game.chatserver.service.ChatService;
import ru.startup.game.chatserver.service.MessageService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {


    private MessageRepository messageRepository;

    private MessageMapper messageMapper;

    private ChatService chatService;


    @Override
    public MessageDto save(MessageDto messageDto) {
        Message message = messageMapper.messageDtoToMessage(messageDto);
        Chat chat = chatService.findModelById(1L);
        message.setChat(chat);
        Message saveMessage = messageRepository.save(message);
        return messageMapper.messageToMessageDto(saveMessage);
    }

    @Override
    public void update(MessageDto messageDto){
        Optional<Message> messageOptional = messageRepository.findById(messageDto.getId());
        messageOptional.ifPresent(message -> messageRepository.save(message));
    }

    @Override
    public void updateMessageLabel(Long messageId, Label findLabel) {
        Optional<Message> findMessage = messageRepository.findById(messageId);
        findMessage.ifPresent(message -> message.getLabels().add(findLabel));
    }
}
