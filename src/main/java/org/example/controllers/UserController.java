package org.example.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.example.models.User;
import org.example.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class UserController {
    private static final String ID = "/{id}";
    private ShopService service;

    @Autowired
    public UserController(ShopService service) {
        this.service = service;
    }

    @GetMapping("/users")
    @JsonView(User.Views.UserSummary.class)
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping(ID)
    @JsonView(User.Views.UserDetails.class)
    public ResponseEntity<User> getUser(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.getUser(id));
    }

    @PostMapping
    @JsonView(User.Views.UserSummary.class)
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = service.createUser(user);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path(ID)
                        .buildAndExpand(createdUser.getId())
                        .toUri()
        ).body(createdUser);
    }

    @PutMapping(ID)
    @JsonView(User.Views.UserSummary.class)
    public ResponseEntity<User> updateUser(@PathVariable("id") UUID id, @Valid @RequestBody User user) {
        return ResponseEntity.ok(service.updateUser(id, user));
    }

    @DeleteMapping(ID)
    public void deleteUser(@PathVariable("id") UUID id) {
        service.deleteUser(id);
    }

}
