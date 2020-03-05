package ru.startup.game.chatserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_dictionary")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private String firstName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "chat_to_user",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_chat"))
    private List<Chat> chats;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Message> messages;
}
