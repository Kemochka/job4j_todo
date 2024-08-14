package todo.model.task;

import lombok.*;
import todo.model.category.Category;
import todo.model.priority.Priority;
import todo.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private LocalDateTime created = LocalDateTime.now(ZoneId.of("UTC"));
    @Column
    private boolean done;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id")
    private Priority priority;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tasks_categories",
            joinColumns = {@JoinColumn(name = "task_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    Set<Category> categories = new HashSet<>();
}
