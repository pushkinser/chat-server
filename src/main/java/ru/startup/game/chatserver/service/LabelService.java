package ru.startup.game.chatserver.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.startup.game.chatserver.model.dto.LabelDto;
import ru.startup.game.chatserver.model.dto.MessageLabelDto;
import ru.startup.game.chatserver.model.entity.Label;
import ru.startup.game.chatserver.model.mapper.LabelMapper;
import ru.startup.game.chatserver.repository.LabelRepository;

@Service
@AllArgsConstructor
public class LabelService {

    private LabelMapper labelMapper;

    private LabelRepository labelRepository;

    private MessageService messageService;

    @Transactional
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
