package pl.ochnios.todobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ochnios.todobackend.models.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
