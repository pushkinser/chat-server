package ru.startup.game.chatserver.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat")
@Getter
@Setter
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chat")
    private List<Message> messages;

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", chatName='" + chatName + '\'' +
                '}';
    }
}
