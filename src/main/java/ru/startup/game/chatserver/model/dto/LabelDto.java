package ru.startup.game.chatserver.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelDto {

    private Long id;
    private String labelName;
    private String type;
}
