package org.example.forum.domain.respuesta;

public record RespuestaResponse(
        Long id,
        String mensaje,
        String autor
) {
    public RespuestaResponse(Respuesta respuesta) {
       this(respuesta.getId(),respuesta.getMensaje(),respuesta.getAutor().getUsername());
    }
}
