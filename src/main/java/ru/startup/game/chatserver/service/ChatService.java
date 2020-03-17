package ru.startup.game.chatserver.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.dto.ChatDto;
import ru.startup.game.chatserver.model.dto.ChatMessageDto;
import ru.startup.game.chatserver.model.dto.MessageDto;
import ru.startup.game.chatserver.model.dto.UserDto;
import ru.startup.game.chatserver.model.entity.Chat;
import ru.startup.game.chatserver.model.mapper.ChatMapper;
import ru.startup.game.chatserver.model.mapper.UserMapper;
import ru.startup.game.chatserver.repository.ChatRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatService {

    private ChatRepository chatRepository;

    private ChatMapper chatMapper;

    private UserMapper userMapper;

    public ChatDto findChatById(Long id) {
        Optional<Chat> chatOptional = chatRepository.findById(id);
        return chatMapper.chatToChatDto(chatOptional.orElse(null));
    }

    public Chat findModelById(Long id) {
        Optional<Chat> chatOptional = chatRepository.findById(id);
        return chatOptional.orElse(null);
    }

    public List<ChatDto> findByUser(UserDto userDto) {
        List<Chat> users = chatRepository.findByUsersEquals(userMapper.userDtoToUser(userDto));
        return chatMapper.chatToChatDto(users);
    }

    public List<ChatMessageDto> getChatHistory() {
        List<ChatMessageDto> chatMessages;
        List<MessageDto> messages = findChatById(1L).getMessages();
        chatMessages = messages.stream()
                .map(messageDto -> new ChatMessageDto(messageDto.getId(), ChatMessageDto.MessageType.CHAT, messageDto.getMessage(), messageDto.getUser().getUserName(), messageDto.getLabels()))
                .collect(Collectors.toList());
        return chatMessages;
    }
}
