package pl.ochnios.todofrontendweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ochnios.todofrontendweb.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
