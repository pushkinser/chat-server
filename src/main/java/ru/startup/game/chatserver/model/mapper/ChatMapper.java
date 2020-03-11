package ru.startup.game.chatserver.model.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.startup.game.chatserver.model.dto.ChatDto;
import ru.startup.game.chatserver.model.entity.Chat;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    ChatDto chatToChatDto (Chat chat);

    Chat chatDtoToChat (ChatDto chatDto);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Chat> chatDtoToChat (List<ChatDto> chatDtos);

    @IterableMapping(qualifiedByName = "toDto")
    List<ChatDto> chatToChatDto (List<Chat> chats);
}
