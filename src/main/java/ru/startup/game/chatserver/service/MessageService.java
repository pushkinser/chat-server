package ru.startup.game.chatserver.service;

import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.dto.MessageDto;
import ru.startup.game.chatserver.model.entity.Message;

@Service
public interface MessageService {

    public void save(MessageDto message);
}
