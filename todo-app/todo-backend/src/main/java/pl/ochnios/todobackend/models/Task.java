package pl.ochnios.todobackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tasks")
@NotNull(message = "The task must not be null")
public class Task {

    public static final int MIN_TITLE_LENGTH = 3;
    public static final int MAX_TITLE_LENGTH = 160;
    public static final int MAX_DESCRIPTION_LENGTH = 4096;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int id;

    @NotNull(message = "The task title must not be null")
    @Size(min = MIN_TITLE_LENGTH, max = MAX_TITLE_LENGTH,
            message = "The title length must be between " + MIN_TITLE_LENGTH + " and " + MAX_TITLE_LENGTH)
    @Column(unique = true, nullable = false)
    private String title;

    @Size(max = MAX_DESCRIPTION_LENGTH,
            message = "The description length must not be longer than " + MAX_DESCRIPTION_LENGTH)
    private String description;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User assigned;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;
}
