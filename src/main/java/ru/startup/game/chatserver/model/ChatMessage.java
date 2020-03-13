package ru.startup.game.chatserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.startup.game.chatserver.model.dto.LabelDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

    private Long id;
    private MessageType type;
    private String content;
    private String sender;
    private List<LabelDto> labels;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}