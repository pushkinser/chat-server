package ru.startup.game.chatserver.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.dto.MessageDto;
import ru.startup.game.chatserver.model.entity.Message;
import ru.startup.game.chatserver.model.mapper.MessageMapper;
import ru.startup.game.chatserver.repository.MessageRepository;
import ru.startup.game.chatserver.service.MessageService;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {


    private MessageRepository messageRepository;

    private MessageMapper messageMapper;

    @Override
    public void save(MessageDto message) {
        messageRepository.save(messageMapper.messageDtoToMessage(message));
    }
}
