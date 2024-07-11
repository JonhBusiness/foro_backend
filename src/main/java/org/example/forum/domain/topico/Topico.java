package org.example.forum.domain.topico;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.forum.domain.curso.Curso;
import org.example.forum.domain.respuesta.Respuesta;
import org.example.forum.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "tbl_topicos")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    @JsonManagedReference
    private Set<Respuesta> respuestas=new HashSet<>();
}
