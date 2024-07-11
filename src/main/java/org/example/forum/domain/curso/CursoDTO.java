package org.example.forum.domain.curso;

public record CursoDTO(
        Long id,
        String nombre,
        String descripcion,
        Long topicos,
        Long respuestas

) {
    public CursoDTO(Curso curso) {
        this(curso.getId(),
        curso.getNombre(),
        curso.getDescripcion(),
        curso.getTopicos()!=null?(long) curso.getTopicos().size():0L,
        curso.getTopicos().stream().mapToLong(c->
                c.getRespuestas()!=null?
                        c.getRespuestas().size():0L).sum());

    }
}

