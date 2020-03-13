package ru.startup.game.chatserver.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.startup.game.chatserver.model.dto.LabelDto;
import ru.startup.game.chatserver.model.dto.MessageLabelDto;
import ru.startup.game.chatserver.model.entity.Label;
import ru.startup.game.chatserver.model.mapper.LabelMapper;
import ru.startup.game.chatserver.repository.LabelRepository;
import ru.startup.game.chatserver.service.LabelService;
import ru.startup.game.chatserver.service.MessageService;

@Service
@AllArgsConstructor
public class LabelServiceImpl implements LabelService {

    private LabelMapper labelMapper;

    private LabelRepository labelRepository;

    private MessageService messageService;

  /*  @Override
    public LabelDto findByLabelName(String labelName){
        return labelMapper.labelToLabelDto(labelRepository.findByLabelName(labelName));
    }

    @Override
    public LabelDto save (LabelDto labelDto){
        Label saveLabel = labelRepository.save(labelMapper.labelDtoToLabel(labelDto));
        return labelMapper.labelToLabelDto(saveLabel);
    }
*/

    @Transactional
    @Override
    public void saveMessageLabel(MessageLabelDto messageLabel) {
        Label findLabel = labelRepository.findByLabelName(messageLabel.getLabelName());
        if( findLabel == null){
            LabelDto labelDto = new LabelDto();
            labelDto.setLabelName(messageLabel.getLabelName());
            findLabel = labelRepository.save(labelMapper.labelDtoToLabel(labelDto));
        }
        messageService.updateMessageLabel(messageLabel.getMessageId(), findLabel);
    }
}
