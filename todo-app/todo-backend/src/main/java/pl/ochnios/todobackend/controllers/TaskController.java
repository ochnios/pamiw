package pl.ochnios.todobackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ochnios.todobackend.dtos.TaskDto;
import pl.ochnios.todobackend.models.Task;
import pl.ochnios.todobackend.services.TaskService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks/{id}")
    public TaskDto get(@PathVariable int id) {
        return TaskDto.mapToDto(taskService.getTask(id));
    }

    @GetMapping("/tasks")
    public List<TaskDto> getAll() {
        List<TaskDto> tasks = new ArrayList<>();

        taskService.getAllTasks().forEach((x) -> tasks.add(TaskDto.mapToDto(x)));

        return tasks;
    }

    @PostMapping("/tasks")
    public TaskDto create(@RequestBody TaskDto dto) {
        return TaskDto.mapToDto(taskService.createTask(taskService.mapFromDto(dto)));
    }

    @PatchMapping("/tasks/{id}")
    public TaskDto update(@PathVariable int id, @RequestBody TaskDto dto) {
        return TaskDto.mapToDto(taskService.updateTask(id, taskService.mapFromDto(dto)));
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable int id) {
        taskService.deleteTask(id);
    }
}
