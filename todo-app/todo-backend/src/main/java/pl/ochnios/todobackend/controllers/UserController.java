package pl.ochnios.todobackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ochnios.todobackend.dtos.UserDto;
import pl.ochnios.todobackend.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable int id) {
        return UserDto.mapToDto(userService.getUser(id));
    }

    @GetMapping
    public List<UserDto> getAll() {
        List<UserDto> users = new ArrayList<>();

        userService.getAllUsers().forEach((user) -> users.add(UserDto.mapToDto(user)));

        return users;
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto dto) {
        return UserDto.mapToDto(userService.createUser(userService.mapFromDto(dto)));
    }

    @PatchMapping("/{id}")
    public UserDto update(@PathVariable int id, @RequestBody UserDto dto) {
        return UserDto.mapToDto(userService.updateUser(id, userService.mapFromDto(dto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
