package pl.ochnios.todobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ochnios.todobackend.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
