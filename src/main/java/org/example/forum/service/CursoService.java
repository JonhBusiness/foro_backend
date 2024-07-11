package org.example.forum.service;

import org.example.forum.domain.curso.Curso;
import org.example.forum.domain.curso.CursoRepository;
import org.example.forum.domain.curso.CursoRequest;
import org.example.forum.exception.CursoAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CursoService {
    private final CursoRepository cursoRepository;


    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;

    }

    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    public Curso crearCurso(CursoRequest cursoRequest) {
        var cursoExistente=cursoRepository.findByNombre(cursoRequest.nombre());
        if (cursoExistente != null) {
            throw new CursoAlreadyExistsException("Ya existe un curso con el nombre: " + cursoRequest.nombre());
        }
        Curso curso = new Curso();
        curso.setNombre(cursoRequest.nombre());
        curso.setDescripcion(cursoRequest.descripcion());
        return cursoRepository.save(curso);
    }

    public Curso findById(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }


}
