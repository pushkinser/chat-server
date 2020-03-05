package ru.startup.game.chatserver.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.Chat;
import ru.startup.game.chatserver.repository.ChatRepository;
import ru.startup.game.chatserver.service.ChatService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {

    private ChatRepository chatRepository;
    @Override
    public Chat findById(Long id) {
        Optional<Chat> chatOptional = chatRepository.findById(id);
        return chatOptional.orElse(null);
    }
}
