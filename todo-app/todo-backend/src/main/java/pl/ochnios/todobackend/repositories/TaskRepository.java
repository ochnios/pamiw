package pl.ochnios.todobackend.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.ochnios.todobackend.models.Task;

public interface TaskRepository extends CrudRepository<Task, Integer> {
}
