package ru.startup.game.chatserver.service;

import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.dto.MessageDto;

@Service
public interface MessageService {

    public MessageDto save(MessageDto message);
}
