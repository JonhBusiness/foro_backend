package org.example.forum.service;


import org.example.forum.domain.respuesta.Respuesta;
import org.example.forum.domain.respuesta.RespuestaRepository;
import org.example.forum.domain.respuesta.RespuestaRequest;
import org.example.forum.domain.topico.Topico;
import org.example.forum.domain.topico.TopicoRepository;
import org.example.forum.domain.usuario.Usuario;
import org.example.forum.domain.usuario.UsuarioRepository;
import org.example.forum.exception.CursoNoEncontradoException;
import org.example.forum.exception.UsuarioNoEncontradoException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RespuestaService {
    private final UsuarioRepository usuarioRepository;
    private final TopicoRepository topicoRepository;
    private final RespuestaRepository respuestaRepository;

    public RespuestaService(UsuarioRepository usuarioRepository, TopicoRepository topicoRepository, RespuestaRepository respuestaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.topicoRepository = topicoRepository;
        this.respuestaRepository = respuestaRepository;
    }

    public Respuesta crearRespuesta(Long id, RespuestaRequest respuestaRequest, String username) {
        Usuario autor = usuarioRepository.findByUsername(username);
        if (autor == null) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado con Username: " + username);
        }

        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new CursoNoEncontradoException("Topico no encontrado con id: " + id));

        Respuesta respuesta = new Respuesta();
        respuesta.setMensaje(respuestaRequest.mensaje());
        respuesta.setFechaCreacion(LocalDateTime.now());
        respuesta.setFechaModificacion(LocalDateTime.now());
        respuesta.setAutor(autor);
        respuesta.setTopico(topico);

        return respuestaRepository.save(respuesta);
    }

}
