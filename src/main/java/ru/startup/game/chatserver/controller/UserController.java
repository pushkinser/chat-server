package ru.startup.game.chatserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.startup.game.chatserver.model.entity.User;
import ru.startup.game.chatserver.repository.UserRepository;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {

    private UserRepository userRepository;

    @GetMapping("user/{id}")
    public String getUserNameById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.map(User::getUserName)
                .orElse("Not found");
    }
}
