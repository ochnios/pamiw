package pl.ochnios.todofrontendweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.ochnios.todofrontendweb.Consts;
import pl.ochnios.todofrontendweb.dtos.ResultsPage;
import pl.ochnios.todofrontendweb.dtos.TaskDto;
import pl.ochnios.todofrontendweb.dtos.TaskStatus;
import pl.ochnios.todofrontendweb.services.TaskService;

import java.util.List;

@RequestMapping("/tasks")
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

        ResultsPage page = taskService.getPaginatedTasks(pageNumber, Consts.PAGE_SIZE, sortField, sortDirection).block();
        List<TaskDto> tasks = page.getResults();

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
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("users", taskService.getAllUsers().block());
        model.addAttribute("categories", taskService.getAllCategories().block());
        return "/tasks/create.html";
    }

    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable int id, Model model) {
        TaskDto task = taskService.getTask(id).block();
        model.addAttribute("task", task);
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("users", taskService.getAllUsers().block());
        model.addAttribute("categories", taskService.getAllCategories().block());
        return "/tasks/update.html";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute("task") TaskDto task) {
        if (task.getId() == 0) {
            // todo: fix error when tasks with the same name is added
            taskService.createTask(task).subscribe();
        } else {
            taskService.updateTask(task.getId(), task).subscribe();
        }

        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id) {
        // todo: fix error when we want to delete task which is assigned to existing task
        this.taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}
