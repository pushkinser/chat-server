package ru.startup.game.chatserver.service;

import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.dto.ChatDto;
import ru.startup.game.chatserver.model.dto.UserDto;

import java.util.List;

@Service
public interface UserService {

    public UserDto findUserByUserName(String userName);

    public List<ChatDto> findChatsByUserName(String userName);
}
