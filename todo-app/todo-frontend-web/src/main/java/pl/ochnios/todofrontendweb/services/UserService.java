package pl.ochnios.todofrontendweb.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.ochnios.todofrontendweb.Consts;
import pl.ochnios.todofrontendweb.dtos.UserDto;
import pl.ochnios.todofrontendweb.models.User;
import pl.ochnios.todofrontendweb.repositories.UserRepository;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final Validator validator;

    @Autowired
    public UserService(UserRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public Iterable<User> getAllUsers(int page) {
        return userRepository.findAll(PageRequest.of(page, Consts.PAGE_SIZE));
    }

    public User createUser(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public User updateUser(int id, User user) {
        User userToUpdate = userRepository.findById(id).orElse(null);

        if (userToUpdate == null) {
            throw new IllegalArgumentException(String.format("The user with id = %d was not found - failed to update", id));
        } else if (user == null) {
            throw new IllegalArgumentException("The updating user must not be null");
        }

        userToUpdate.setName(user.getName());
        userToUpdate.setSurname(user.getSurname());
        userToUpdate.setEmail(user.getEmail());

        Set<ConstraintViolation<User>> violations = validator.validate(userToUpdate);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return userRepository.save(userToUpdate);
    }

    public User mapFromDto(UserDto dto) {
        return dto == null ? null : User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .build();
    }
}
