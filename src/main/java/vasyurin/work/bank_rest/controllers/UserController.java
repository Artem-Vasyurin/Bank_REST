package vasyurin.work.bank_rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vasyurin.work.bank_rest.dto.UserDto;
import vasyurin.work.bank_rest.dto.UserRequestDto;
import vasyurin.work.bank_rest.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userSecurity.isUserOwner(principal, #id)")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> createUser(@RequestBody UserRequestDto dto) {
        UserDto createdUser = userService.createUser(dto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userSecurity.isUserOwner(principal, #id)")
    public ResponseEntity<UserDto> updateUser(@PathVariable int id, @RequestBody UserRequestDto dto) {
        UserDto updatedUser = userService.updateUser(id, dto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
