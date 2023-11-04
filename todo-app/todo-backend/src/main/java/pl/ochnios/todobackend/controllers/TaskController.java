package pl.ochnios.todobackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ochnios.todobackend.dtos.TaskDto;
import pl.ochnios.todobackend.models.Task;
import pl.ochnios.todobackend.services.TaskService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/tasks")
@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> get(@PathVariable int id) {
        TaskDto task = TaskDto.mapToDto(taskService.getTask(id));
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll() {
        List<TaskDto> tasks = new ArrayList<>();
        taskService.getAllTasks().forEach((x) -> tasks.add(TaskDto.mapToDto(x)));

        return !tasks.isEmpty() ? ResponseEntity.ok(tasks) : ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<TaskDto> create(@RequestBody TaskDto dto) {
        TaskDto createdTask = TaskDto.mapToDto(taskService.createTask(taskService.mapFromDto(dto)));
        return new ResponseEntity<TaskDto>(createdTask, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable int id, @RequestBody TaskDto dto) {
        TaskDto updatedTask = TaskDto.mapToDto(taskService.updateTask(id, taskService.mapFromDto(dto)));
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}