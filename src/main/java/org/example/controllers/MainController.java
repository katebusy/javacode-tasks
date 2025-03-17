package org.example.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/home")
    public ResponseEntity<String> getGreetingGuest() {
        return ResponseEntity.ok("Hello, guest! This is unprotected page!");
    }
    @GetMapping("/user")
    public ResponseEntity<String> getGreetingUser() {

        return ResponseEntity.ok("Hello, user! This is page for users!");
    }
}
