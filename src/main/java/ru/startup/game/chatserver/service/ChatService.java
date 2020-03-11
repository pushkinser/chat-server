package ru.startup.game.chatserver.service;

import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.dto.ChatDto;
import ru.startup.game.chatserver.model.dto.UserDto;
import ru.startup.game.chatserver.model.entity.Chat;

import java.util.List;

@Service
public interface ChatService {

    public ChatDto findById(Long id);

    public Chat findModelById (Long id);

    public List<ChatDto> findByUser(UserDto userDto);
}
