package pl.ochnios.todobackend.services;

import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ochnios.todobackend.models.User;
import pl.ochnios.todobackend.repositories.UserRepository;

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
}
