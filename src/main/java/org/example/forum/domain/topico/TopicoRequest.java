package org.example.forum.domain.topico;

import jakarta.validation.constraints.NotNull;

public record TopicoRequest(
        @NotNull(message = "El Titulo no debe ser vacio o nulo")
        String titulo,
        @NotNull(message = "Mensaje No puede ser nulo")
        String mensaje

) {
}
