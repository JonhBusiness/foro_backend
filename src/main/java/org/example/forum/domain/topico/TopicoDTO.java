package org.example.forum.domain.topico;

public record TopicoDTO(Long id, String titulo,String mensaje, String usuario, String curso, Long numRespuestas) {
    public TopicoDTO(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getUsuario().getUsername(),
                topico.getCurso().getNombre(),
                topico.getRespuestas() != null ? (long)
                        topico.getRespuestas().size() : 0L);
    }

}
