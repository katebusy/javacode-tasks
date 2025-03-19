package org.example.controllers;

import org.example.dtos.ReqRes;
import org.example.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/sign-up")
    public ResponseEntity<ReqRes> signUp(@RequestBody ReqRes signUpRequest) {
        ReqRes body = authService.signUp(signUpRequest);
        if (body.getStatusCode() == 200) {
            return ResponseEntity.ok(body);
        } else {
            return ResponseEntity.badRequest().body(body);
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ReqRes> signIn(@RequestBody ReqRes signInRequest) {
        ReqRes body = authService.signIn(signInRequest);
        if (body.getStatusCode() == 200) {
            return ResponseEntity.ok(body);
        } else {
            return ResponseEntity.badRequest().body(body);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes refreshTokenRequest) {
        ReqRes body = authService.refreshToken(refreshTokenRequest);
        if (body.getStatusCode() == 200) {
            return ResponseEntity.ok(body);
        } else {
            return ResponseEntity.badRequest().body(body);
        }
    }
}
