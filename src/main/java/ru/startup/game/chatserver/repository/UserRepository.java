package ru.startup.game.chatserver.repository;

import org.springframework.data.repository.CrudRepository;
import ru.startup.game.chatserver.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
