package ru.startup.game.chatserver.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_chat", referencedColumnName = "id")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @Column
    private  String message;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", chat=" + chat.getId() +
                ", user=" + user.getId() +
                ", message='" + message + '\'' +
                '}';
    }
}
