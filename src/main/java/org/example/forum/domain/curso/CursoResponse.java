package org.example.forum.domain.curso;

import org.example.forum.domain.topico.TopicoDTO;
import org.example.forum.domain.topico.TopicoResponse;

import java.util.List;

public record CursoResponse(
        Long id,
        String nombre,
        List<TopicoDTO> topicos
//        List<TopicoResponse> topicos
) {
    public CursoResponse(Curso curso) {
        this(curso.getId(),
                curso.getNombre(),
                curso.getTopicos().stream().map(TopicoDTO::new).toList());
    }
//    public CursoResponse(Curso curso) {
//     this(curso.getId(),
//             curso.getNombre(),
//             curso.getTopicos().stream().map(TopicoResponse::new).toList());
//    }
}
