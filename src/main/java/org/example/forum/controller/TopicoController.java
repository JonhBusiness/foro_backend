package org.example.forum.controller;

import jakarta.validation.Valid;
import org.example.forum.domain.topico.Topico;
import org.example.forum.domain.topico.TopicoDTO;
import org.example.forum.domain.topico.TopicoRequest;
import org.example.forum.domain.topico.TopicoResponse;
import org.example.forum.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping("/{id}/respuestas")
    public ResponseEntity<?> buscarReplies(@PathVariable Long id) {
        Topico topico=topicoService.findRepliesById(id);
        if (topico == null) {
            return ResponseEntity.notFound().build();
        }
        var response=new TopicoResponse(topico);
        return ResponseEntity.ok(response);
    }

//El id es el id del curso selecionado
    @PostMapping( "/{id}/crear")
    public ResponseEntity<?> crearTopicos(@RequestBody  @Valid TopicoRequest topicoRequest, @PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Topico topico = topicoService.crearTopico(id,topicoRequest,userDetails.getUsername());
        var response=new TopicoDTO(topico);
        return ResponseEntity.ok(response);
    }
}
