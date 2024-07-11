package org.example.forum.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.example.forum.domain.curso.Curso;
import org.example.forum.domain.curso.CursoDTO;
import org.example.forum.domain.curso.CursoRequest;
import org.example.forum.domain.curso.CursoResponse;
import org.example.forum.service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@SecurityRequirement(name = "bearer-key")
@SuppressWarnings("all")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }
    @PostMapping( "/curso/crear")
    @Operation(
            summary = "registra un Curso en la base de datos",
            description = "",
            tags = { "Curso", "post" })
    public ResponseEntity<?> crearCurso(@RequestBody @Valid CursoRequest cursoRequest) {
        Curso curso = cursoService.crearCurso(cursoRequest);
        var response = new CursoDTO(curso);
        return ResponseEntity.ok(response);
    }
    @Operation(summary = "Obtiene el listado de Cursos")
    @GetMapping("/cursos")
    public ResponseEntity<?> listaCursos(){
        var response=cursoService.listar().stream().map(CursoDTO::new);
        return ResponseEntity.ok(response);
    }
    @GetMapping("curso/{id}/topics")
    @Operation(summary = "Obtiene los Topicos por Curso")
    public ResponseEntity<?> buscarTopicsByCurso(@PathVariable Long id) {
        Curso curso=cursoService.findById(id);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        }
        var response=new CursoResponse(curso);
        return ResponseEntity.ok(response);
    }

}
