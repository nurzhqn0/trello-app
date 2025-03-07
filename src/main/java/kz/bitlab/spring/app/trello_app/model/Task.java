package kz.bitlab.spring.app.trello_app.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private TaskStatus status = TaskStatus.TODO;

    @ManyToOne
    private Folder folder; // Many To One
}

