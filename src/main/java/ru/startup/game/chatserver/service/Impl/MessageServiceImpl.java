package ru.startup.game.chatserver.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.Message;
import ru.startup.game.chatserver.repository.MessageRepository;
import ru.startup.game.chatserver.service.MessageService;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {


    private MessageRepository messageRepository;

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }
}
