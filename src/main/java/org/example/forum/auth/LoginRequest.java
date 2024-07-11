package org.example.forum.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Debe ingresar su Usuario")
        String username,
        @NotBlank(message = "Ingrese su Password")
        String password
) {
}
