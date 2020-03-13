package ru.startup.game.chatserver.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageLabelDto {

    private Long messageId;
    private String labelName;
}
