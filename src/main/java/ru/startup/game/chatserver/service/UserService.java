package ru.startup.game.chatserver.service;

import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.User;

@Service
public interface UserService {

    public User findUserByUserName(String userName);
}
