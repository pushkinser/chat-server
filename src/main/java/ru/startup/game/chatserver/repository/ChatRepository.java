package ru.startup.game.chatserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.startup.game.chatserver.model.entity.Chat;
import ru.startup.game.chatserver.model.entity.User;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    public List<Chat> findByUsersEquals(User user);
}
