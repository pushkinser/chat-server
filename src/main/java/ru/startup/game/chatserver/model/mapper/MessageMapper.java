package ru.startup.game.chatserver.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.startup.game.chatserver.model.dto.ChatMessageDto;
import ru.startup.game.chatserver.model.dto.MessageDto;
import ru.startup.game.chatserver.model.entity.Message;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    Message messageDtoToMessage(MessageDto messageDto);

    MessageDto messageToMessageDto(Message message);

    @Mapping(target = "sender", source = "user.userName")
    @Mapping(target = "content", source = "message")
    ChatMessageDto dtoToChatMessageDto(MessageDto messageDto);

    @Mapping(target = "sender", source = "user.userName")
    @Mapping(target = "content", source = "message")
    ChatMessageDto modelToChatMessageDto(Message message);
}
