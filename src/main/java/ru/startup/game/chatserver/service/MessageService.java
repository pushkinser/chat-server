package ru.startup.game.chatserver.service;

import org.springframework.stereotype.Service;
import ru.startup.game.chatserver.model.dto.MessageDto;
import ru.startup.game.chatserver.model.entity.Label;

@Service
public interface MessageService {

    public MessageDto save(MessageDto message);

    public void update(MessageDto message);

    public void updateMessageLabel(Long messageId, Label findLabel);
}
