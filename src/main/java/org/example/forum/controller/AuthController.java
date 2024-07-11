package org.example.forum.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.forum.auth.LoginRequest;
import org.example.forum.auth.RegisterRequest;
import org.example.forum.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticacion", description = "obtiene el token para el usuario asignado que da acceso al resto de endpoint")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")

    public ResponseEntity<?> authenticateLogin(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            String jwt = authService.loginUser(loginRequest);
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

    }

    @PostMapping("/register")
    public ResponseEntity<?> authenticateRegister(@RequestBody @Valid RegisterRequest registerRequest) {

        return authService.registerUser(registerRequest);
    }

    private record JwtAuthenticationResponse(String jwt) {
    }
}

