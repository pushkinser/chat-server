package ru.startup.game.chatserver.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private Long id;
    private UserDto user;
    private String message;
}
