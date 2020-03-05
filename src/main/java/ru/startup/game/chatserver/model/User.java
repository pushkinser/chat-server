package ru.startup.game.chatserver.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_dictionary")
@Getter
@Setter
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Message> messages;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
