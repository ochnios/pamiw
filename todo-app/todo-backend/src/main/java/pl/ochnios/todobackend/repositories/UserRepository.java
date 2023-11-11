package pl.ochnios.todobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ochnios.todobackend.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
