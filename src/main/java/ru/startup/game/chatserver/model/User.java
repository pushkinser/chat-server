package ru.startup.game.chatserver.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
public class User {

    @Id
    private Long id;

    private String userName;

    private String firstName;
}
