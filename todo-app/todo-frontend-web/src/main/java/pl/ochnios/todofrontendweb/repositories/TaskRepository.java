package pl.ochnios.todofrontendweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ochnios.todofrontendweb.models.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
