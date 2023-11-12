package pl.ochnios.todobackend.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.ochnios.todobackend.dtos.TaskDto;
import pl.ochnios.todobackend.models.Category;
import pl.ochnios.todobackend.models.Task;
import pl.ochnios.todobackend.models.TaskStatus;
import pl.ochnios.todobackend.models.User;
import pl.ochnios.todobackend.repositories.TaskRepository;

import java.util.List;
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

    public Page<Task> getPaginatedTasks(int pageNumber, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

        return taskRepository.findAll(pageable);
    }

    public Task createTask(Task task) {
        Set<ConstraintViolation<Task>> violations = validator.validate(task);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        task.setStatus(TaskStatus.New);
        
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

    public List<User> getAllUsers() {
        return userService.getALlUsers();
    }

    public List<Category> getAllCategories() {
        return categoryService.getALlCategories();
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
