package ru.startup.game.chatserver.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.dto.ChatDto;
import ru.startup.game.chatserver.model.dto.UserDto;
import ru.startup.game.chatserver.model.entity.Chat;
import ru.startup.game.chatserver.model.mapper.ChatMapper;
import ru.startup.game.chatserver.model.mapper.UserMapper;
import ru.startup.game.chatserver.repository.ChatRepository;
import ru.startup.game.chatserver.service.ChatService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private ChatRepository chatRepository;

    private ChatMapper chatMapper;

    private UserMapper userMapper;

    @Override
    public ChatDto findById(Long id) {
        Optional<Chat> chatOptional = chatRepository.findById(id);
        return chatMapper.chatToChatDto(chatOptional.orElse(null));
    }

    @Override
    public Chat findModelById(Long id) {
        Optional<Chat> chatOptional = chatRepository.findById(id);
        return chatOptional.orElse(null);
    }

    @Override
    public List<ChatDto> findByUser(UserDto userDto){
        List<Chat> users = chatRepository.findByUsersEquals(userMapper.userDtoToUser(userDto));
        return chatMapper.chatToChatDto(users);
    }
}
