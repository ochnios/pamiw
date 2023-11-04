package pl.ochnios.todobackend.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
@NotNull(message = "The user must not be null")
public class User {

    public static final int MIN_NAME_LENGTH = 3;
    public static final int MAX_NAME_LENGTH = 50;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @NotNull(message = "The user name must not be null.")
    @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH,
            message = "The user name must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH)
    private String name;

    @NotNull(message = "The user surname must not be null.")
    @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH,
            message = "The user surname must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH)
    private String surname;

    @NotNull(message = "The user email must not be null")
    @Email(message = "The user email must be in valid format")
    private String email;

    @Valid
    @OneToMany(mappedBy = "assigned", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Task> tasks = new ArrayList<>();
}
