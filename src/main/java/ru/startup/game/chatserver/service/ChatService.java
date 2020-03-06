package ru.startup.game.chatserver.service;

import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.dto.ChatDto;

@Service
public interface ChatService {

    public ChatDto findById(Long id);
}
