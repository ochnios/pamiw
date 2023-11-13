package pl.ochnios.todofrontendweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ochnios.todofrontendweb.dtos.UserDto;
import pl.ochnios.todofrontendweb.services.UserService;

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
    public ResponseEntity<List<UserDto>> getAll(@RequestParam(required = false) Integer page) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        List<UserDto> users = new ArrayList<>();

        userService.getAllUsers(pageNumber).forEach((user) -> users.add(UserDto.mapToDto(user)));

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
