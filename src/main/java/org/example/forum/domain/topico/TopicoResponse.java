package org.example.forum.domain.topico;


import org.example.forum.domain.respuesta.RespuestaDTO;


import java.util.List;

public record TopicoResponse(Long id,
                             String titulo,
                             String mensaje,
                             String usuario ,
                             Long numRespuestas,
                             List<RespuestaDTO> respuestas)
//                             List<RespuestaResponse> respuestas)
{
    public TopicoResponse(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getUsuario().getUsername(),
                (long) topico.getRespuestas().size(),
                topico.getRespuestas().stream().map(RespuestaDTO::new).toList());
    }
//    public TopicoResponse(Topico topico) {
//      this(topico.getId(),
//              topico.getTitulo(),
//              topico.getMensaje(),
//              topico.getUsuario().getUsername(),
//              (long) topico.getRespuestas().size(),
//              topico.getRespuestas().stream().map(RespuestaResponse::new).toList());
//    }
}
