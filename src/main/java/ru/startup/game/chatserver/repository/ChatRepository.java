package ru.startup.game.chatserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.startup.game.chatserver.model.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}