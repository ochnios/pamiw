package pl.ochnios.todobackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.ochnios.todobackend.Consts;
import pl.ochnios.todobackend.dtos.CategoryDto;
import pl.ochnios.todobackend.dtos.TaskDto;
import pl.ochnios.todobackend.dtos.UserDto;
import pl.ochnios.todobackend.models.Task;
import pl.ochnios.todobackend.models.TaskStatus;
import pl.ochnios.todobackend.services.TaskService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/tasks")
@Controller
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getPaginated(@RequestParam(required = false) Integer pageNumber,
                               @RequestParam(required = false) String sortField,
                               @RequestParam(required = false) String sortDirection,
                               Model model) {

        pageNumber = pageNumber != null && pageNumber >= 1 ? pageNumber : 1;
        sortField = sortField != null ? sortField : "id";
        sortDirection = sortDirection != null ? sortDirection : "asc";

        Page<Task> page = taskService.getPaginatedTasks(pageNumber, Consts.PAGE_SIZE, sortField, sortDirection);

        List<TaskDto> tasks = new ArrayList<>();
        page.getContent().forEach((x) -> tasks.add(TaskDto.mapToDto(x)));

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");

        model.addAttribute("tasks", tasks);

        return "/tasks/tasks.html";
    }

    @GetMapping("/create")
    public String getCreationForm(Model model) {
        TaskDto task = new TaskDto();
        model.addAttribute("task", task);
        model.addAttribute("users", getAllUsers());
        model.addAttribute("categories", getAllCategories());
        return "/tasks/create.html";
    }

    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable int id, Model model) {
        TaskDto task = TaskDto.mapToDto(taskService.getTask(id));
        model.addAttribute("task", task);
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("users", getAllUsers());
        model.addAttribute("categories", getAllCategories());
        return "/tasks/update.html";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute("task") TaskDto task) {
        if (task.getId() == 0) {
            taskService.createTask(taskService.mapFromDto(task));
        } else {
            taskService.updateTask(task.getId(), taskService.mapFromDto(task));
        }

        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id) {
        this.taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    private List<UserDto> getAllUsers() {
        List<UserDto> users = new ArrayList<>();
        taskService.getAllUsers().forEach((user) -> users.add(UserDto.mapToDto(user)));
        return users;
    }

    private List<CategoryDto> getAllCategories() {
        List<CategoryDto> categories = new ArrayList<>();
        taskService.getAllCategories().forEach((category) -> categories.add(CategoryDto.mapToDto(category)));
        return categories;
    }
}
