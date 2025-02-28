package org.example.controller;

import org.example.model.resource.UserResource;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        List<UserResource> users = userService.loadAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long id) {
        UserResource user = userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/by-flight/{flightId}")
    public ResponseEntity<List<UserResource>> getUsersByFlight(@PathVariable Long flightId) {
        List<UserResource> users = userService.findUsersByFlight(flightId);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserResource> createOrUpdateUser(@RequestBody UserResource userResource) {
        UserResource savedUser = userService.saveUser(userResource);
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
