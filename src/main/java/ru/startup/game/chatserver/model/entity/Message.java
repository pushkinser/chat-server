package ru.startup.game.chatserver.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @ManyToMany
    @JoinTable(name = "label_to_message",
            joinColumns = @JoinColumn(name = "id_label"),
            inverseJoinColumns = @JoinColumn(name = "id_message"))
    private List<Label> labels;

    @Column
    private  String message;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", user=" + user.getId() +
                ", message='" + message + '\'' +
                '}';
    }
}
