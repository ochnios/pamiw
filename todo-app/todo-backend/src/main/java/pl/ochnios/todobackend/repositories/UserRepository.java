package pl.ochnios.todobackend.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.ochnios.todobackend.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
