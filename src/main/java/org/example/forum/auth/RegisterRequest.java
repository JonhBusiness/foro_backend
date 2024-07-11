package org.example.forum.auth;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank(message = "Username esta vacio")
        String username,
        @NotBlank(message = "Password esta vacio")
        String password ,
        @NotBlank(message = "Email esta vacio")
        String email
) {
}
