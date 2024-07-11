package org.example.forum.domain.curso;

import jakarta.validation.constraints.NotNull;

public record CursoRequest(
        @NotNull(message = "El nombre no debe ser vacio o nulo")
        String nombre,
        @NotNull(message = "La descripcion No puede ser nulo")
        String descripcion
) {
}

