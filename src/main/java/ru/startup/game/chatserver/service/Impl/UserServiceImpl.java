package ru.startup.game.chatserver.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.dto.UserDto;
import ru.startup.game.chatserver.model.entity.User;
import ru.startup.game.chatserver.model.mapper.ChatMapper;
import ru.startup.game.chatserver.model.mapper.UserMapper;
import ru.startup.game.chatserver.repository.UserRepository;
import ru.startup.game.chatserver.service.UserService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    private ChatMapper chatMapper;

    @Override
    public UserDto findUserByUserName(String userName) {
        Optional<User> userOptional = userRepository.findUserByUserName(userName);
        return userMapper.userToUserDto(userOptional.orElse(null));
    }


}
