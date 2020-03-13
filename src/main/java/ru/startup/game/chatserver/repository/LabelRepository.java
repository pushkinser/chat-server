package ru.startup.game.chatserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.startup.game.chatserver.model.entity.Label;

public interface LabelRepository extends JpaRepository<Label,Long> {

    public Label findByLabelName(String labelName);
}
