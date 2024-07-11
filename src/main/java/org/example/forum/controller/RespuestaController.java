package org.example.forum.controller;

import jakarta.validation.Valid;
import org.example.forum.domain.respuesta.Respuesta;
import org.example.forum.domain.respuesta.RespuestaDTO;
import org.example.forum.domain.respuesta.RespuestaRequest;
import org.example.forum.service.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    //El id es el id del del topico selecionado
    @PostMapping( "{id}/crear")
    public ResponseEntity<?> crearRespuesta(@RequestBody @Valid RespuestaRequest respuestaRequest, @PathVariable Long id,@AuthenticationPrincipal UserDetails userDetails) {

        Respuesta respuesta = respuestaService.crearRespuesta(id,respuestaRequest, userDetails.getUsername());
        var response=new RespuestaDTO(respuesta);
        return ResponseEntity.ok(response);
    }
}
