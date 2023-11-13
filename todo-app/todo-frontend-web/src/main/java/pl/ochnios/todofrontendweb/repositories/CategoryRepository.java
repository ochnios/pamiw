package pl.ochnios.todofrontendweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ochnios.todofrontendweb.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
