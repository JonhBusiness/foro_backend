package org.example.forum.domain.respuesta;

import jakarta.validation.constraints.NotBlank;

public record RespuestaRequest(
        @NotBlank(message = "El campo mensaje esta vacio")
        String mensaje

) {
}
