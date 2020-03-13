package ru.startup.game.chatserver.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.startup.game.chatserver.model.dto.LabelDto;
import ru.startup.game.chatserver.model.entity.Label;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LabelMapper {

    LabelMapper INSTANCE = Mappers.getMapper(LabelMapper.class);

    Label labelDtoToLabel(LabelDto labelDto);

    LabelDto labelToLabelDto(Label label);


    List<Label> labelDtoToLabelList (List<LabelDto> labelDtos);


    List<LabelDto> labelToLabelDtoList (List<Label> labels);
}
