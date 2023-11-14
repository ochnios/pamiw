package pl.ochnios.todofrontendweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.ochnios.todofrontendweb.conf.Consts;
import pl.ochnios.todofrontendweb.dtos.ResultsPage;
import pl.ochnios.todofrontendweb.dtos.UserDto;
import pl.ochnios.todofrontendweb.services.UserService;

import java.util.List;

@RequestMapping("/users")
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getPaginated(@RequestParam(required = false) Integer pageNumber,
                               @RequestParam(required = false) String sortField,
                               @RequestParam(required = false) String sortDirection,
                               Model model) {

        pageNumber = pageNumber != null && pageNumber >= 1 ? pageNumber : 1;
        sortField = sortField != null ? sortField : "id";
        sortDirection = sortDirection != null ? sortDirection : "asc";

        ResultsPage page = userService.getPaginatedUsers(pageNumber, Consts.PAGE_SIZE, sortField, sortDirection).block();
        List<UserDto> users = page.getResults();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");

        model.addAttribute("users", users);

        return "/users/users.html";
    }

    @GetMapping("/create")
    public String getCreationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "/users/create.html";
    }

    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable int id, Model model) {
        UserDto user = userService.getUser(id).block();
        model.addAttribute("user", user);
        return "/users/update.html";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") UserDto user) {
        if (user.getId() == 0) {
            // todo: fix error when users with the same name is added
            userService.createUser(user).subscribe();
        } else {
            userService.updateUser(user.getId(), user).subscribe();
        }

        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id) {
        // todo: fix error when we want to delete user which is assigned to existing task
        this.userService.deleteUser(id);
        return "redirect:/users";
    }
}
