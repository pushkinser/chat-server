package ru.startup.game.chatserver.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.User;
import ru.startup.game.chatserver.repository.UserRepository;
import ru.startup.game.chatserver.service.UserService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User findUserByUserName(String userName) {
        Optional<User> userOptional = userRepository.findUserByUserName(userName);
        return userOptional.orElse(null);
    }
}
