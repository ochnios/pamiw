package pl.ochnios.todobackend.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ochnios.todobackend.dtos.TaskDto;
import pl.ochnios.todobackend.models.Task;
import pl.ochnios.todobackend.repositories.TaskRepository;

import java.util.Set;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final Validator validator;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public TaskService(TaskRepository taskRepository, Validator validator, UserService userService, CategoryService categoryService) {
        this.taskRepository = taskRepository;
        this.validator = validator;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public Task getTask(int id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        Set<ConstraintViolation<Task>> violations = validator.validate(task);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return taskRepository.save(task);
    }

    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(int id, Task task) {
        Task taskToUpdate = taskRepository.findById(id).orElse(null);

        if (taskToUpdate == null) {
            throw new IllegalArgumentException(String.format("The task with id = %d was not found - failed to update", id));
        } else if (task == null) {
            throw new IllegalArgumentException("The updating task must not be null");
        }

        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setStatus(task.getStatus());
        taskToUpdate.setCategory(task.getCategory());
        taskToUpdate.setAssigned(task.getAssigned());

        Set<ConstraintViolation<Task>> violations = validator.validate(taskToUpdate);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return taskRepository.save(taskToUpdate);
    }

    public Task mapFromDto(TaskDto dto) {
        return dto == null ? null : Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .assigned(dto.getUserId() != null ? userService.getUser(dto.getUserId()) : null)
                .category(dto.getCategoryId() != null ? categoryService.getCategory(dto.getCategoryId()) : null)
                .build();
    }
}
