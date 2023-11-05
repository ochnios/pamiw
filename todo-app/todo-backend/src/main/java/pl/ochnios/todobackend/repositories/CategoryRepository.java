package pl.ochnios.todobackend.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.ochnios.todobackend.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
