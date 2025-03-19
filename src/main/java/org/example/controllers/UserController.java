package org.example.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/user")
    public ResponseEntity<Object> bothAdminsAndUsersApi() {
        return ResponseEntity.ok("Both Admin and Users can access this Api");
    }

    @GetMapping("/admin")
    public ResponseEntity<Object> adminAlone() {
        return ResponseEntity.ok("Admin alone can access this Api only");
    }
}
