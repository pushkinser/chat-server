package ru.startup.game.chatserver.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "label_name")
    private String labelName;

    @Column(name = "type")
    private String type;

    @ManyToMany
    @JoinTable(name = "label_to_message",
               joinColumns = @JoinColumn(name = "id_label"),
               inverseJoinColumns = @JoinColumn(name = "id_message"))
    private List<Message> messages;
}
