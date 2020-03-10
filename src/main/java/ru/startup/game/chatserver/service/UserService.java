package ru.startup.game.chatserver.service;

import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.dto.UserDto;

@Service
public interface UserService {

    public UserDto findUserByUserName(String userName);
}
