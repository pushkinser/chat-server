package ru.startup.game.chatserver.service;

import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.Message;

@Service
public interface MessageService {

    public void save(Message message);
}
