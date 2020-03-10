package ru.startup.game.chatserver.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.dto.ChatDto;
import ru.startup.game.chatserver.model.entity.Chat;
import ru.startup.game.chatserver.model.mapper.ChatMapper;
import ru.startup.game.chatserver.repository.ChatRepository;
import ru.startup.game.chatserver.service.ChatService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private ChatRepository chatRepository;

    private ChatMapper chatMapper;

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
}
