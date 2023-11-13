package pl.ochnios.todobackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.ochnios.todobackend.dtos.ResultsPage;
import pl.ochnios.todobackend.dtos.UserDto;
import pl.ochnios.todobackend.models.User;
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
    public ResponseEntity<UserDto> get(@PathVariable int id) {
        UserDto user = UserDto.mapToDto(userService.getUser(id));
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<ResultsPage<UserDto>> getPaginated(@RequestParam(required = false) Integer pageNumber,
                                                             @RequestParam(required = false) Integer pageSize,
                                                             @RequestParam(required = false) String sortField,
                                                             @RequestParam(required = false) String sortDirection,
                                                             Model model) {

        pageNumber = pageNumber != null && pageNumber >= 1 ? pageNumber : 1;
        pageSize = pageSize != null && pageSize >= 1 ? pageSize : 5;
        sortField = sortField != null ? sortField : "id";
        sortDirection = sortDirection != null ? sortDirection : "asc";

        Page<User> page = userService.getPaginatedUsers(pageNumber, pageSize, sortField, sortDirection);

        List<UserDto> users = new ArrayList<>();
        page.getContent().forEach((x) -> users.add(UserDto.mapToDto(x)));

        ResultsPage results = new ResultsPage(users, page.getNumber() + 1, page.getTotalPages(), page.getTotalElements());

        return !users.isEmpty() ? ResponseEntity.ok(results) : ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> users = new ArrayList<>();
        userService.getAllUsers().forEach((x) -> users.add(UserDto.mapToDto(x)));

        return !users.isEmpty() ? ResponseEntity.ok(users) : ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto dto) {
        UserDto createdUser = UserDto.mapToDto(userService.createUser(userService.mapFromDto(dto)));
        return new ResponseEntity<UserDto>(createdUser, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable int id, @RequestBody UserDto dto) {
        UserDto updatedUser = UserDto.mapToDto(userService.updateUser(id, userService.mapFromDto(dto)));
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
