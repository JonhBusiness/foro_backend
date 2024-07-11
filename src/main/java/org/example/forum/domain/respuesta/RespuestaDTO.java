package org.example.forum.domain.respuesta;

public record RespuestaDTO(
        Long id,
        String mensaje,
        String autor,
        String topico,
        String pregunta
) {
    public RespuestaDTO( Respuesta respuesta) {
      this(respuesta.getId(),
              respuesta.getMensaje(),
              respuesta.getAutor().getUsername(),
              respuesta.getTopico().getTitulo(),
              respuesta.getTopico().getMensaje());
    }
}
