package ru.startup.game.chatserver.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.dto.UserDto;
import ru.startup.game.chatserver.model.entity.User;
import ru.startup.game.chatserver.model.mapper.UserMapper;
import ru.startup.game.chatserver.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    public UserDto findUserByUserName(String userName) {
        Optional<User> userOptional = userRepository.findUserByUserName(userName);
        return userMapper.userToUserDto(userOptional.orElse(null));
    }


}
