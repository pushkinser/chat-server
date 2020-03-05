package ru.startup.game.chatserver.service;

import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.Chat;

@Service
public interface ChatService {

    public Chat findById(Long id);
}
