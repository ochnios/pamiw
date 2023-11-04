package pl.ochnios.todobackend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
@NotNull(message = "The category must not be null")
public class Category {

    public static final int MIN_NAME_LENGTH = 3;
    public static final int MAX_NAME_LENGTH = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;

    @NotNull(message = "The category name must not be null")
    @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH,
            message = "The category name must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH)
    @Column(unique = true, nullable = false)
    private String name;
}
