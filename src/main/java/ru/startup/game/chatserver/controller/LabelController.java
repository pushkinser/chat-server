package ru.startup.game.chatserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.startup.game.chatserver.model.dto.MessageLabelDto;
import ru.startup.game.chatserver.service.LabelService;

@RestController
@AllArgsConstructor
public class LabelController {

    private LabelService labelService;

    @PostMapping("label")
    public void saveMessageLabel (@RequestBody MessageLabelDto messageLabel){
        labelService.saveMessageLabel(messageLabel);
    }
}
