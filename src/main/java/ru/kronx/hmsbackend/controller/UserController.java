package ru.kronx.hmsbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kronx.hmsbackend.entity.User;
import ru.kronx.hmsbackend.exception.EmptyRequeredFieldException;
import ru.kronx.hmsbackend.exception.NoEntityException;
import ru.kronx.hmsbackend.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        return !users.isEmpty() ? ResponseEntity.ok(users) :
                new ResponseEntity("Список пользователей пуст", HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(userService.findById(id));
        } catch (NoEntityException | EmptyRequeredFieldException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return new ResponseEntity(HttpStatus.OK);
        } catch (EmptyRequeredFieldException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    @PostMapping("/new")
    public ResponseEntity createUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.createUser(user));
        } catch (EmptyRequeredFieldException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @PostMapping("/enabled/{id}")
    public ResponseEntity enabledUser(@PathVariable("id") Long id) {
        if (id == null || id == 0) {
            return new ResponseEntity<>("Некорректный id", HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            userService.enabledUser(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoEntityException e) {
            throw new RuntimeException(e);
        }
    }

}
