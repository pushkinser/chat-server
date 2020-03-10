package ru.startup.game.chatserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.startup.game.chatserver.model.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
