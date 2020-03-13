package ru.startup.game.chatserver.service;

import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.dto.MessageLabelDto;

@Service
public interface LabelService {

   /* public LabelDto findByLabelName(String labelName);

    public LabelDto save(LabelDto labelDto);*/

    public void saveMessageLabel(MessageLabelDto messageLabel);
}
