package ru.startup.game.chatserver.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.startup.game.chatserver.model.dto.ChatDto;
import ru.startup.game.chatserver.model.entity.Chat;

@Mapper
public interface ChatMapper {

    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    ChatDto chatToChatDto (Chat chat);

    Chat chatDtoToChat (ChatDto chatDto);
}
