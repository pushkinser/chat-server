package ru.startup.game.chatserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat_message")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_name")
    private String chatName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "chat_to_user",
               joinColumns = @JoinColumn(name = "id_chat"),
               inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<User> users;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Message> messages;
}
