package ru.startup.game.chatserver.service;

import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.dto.ChatDto;
import ru.startup.game.chatserver.model.entity.Chat;

@Service
public interface ChatService {

    public ChatDto findById(Long id);

    public Chat findModelById (Long id);
}
