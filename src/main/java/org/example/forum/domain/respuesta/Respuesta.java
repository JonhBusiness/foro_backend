package org.example.forum.domain.respuesta;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.forum.domain.topico.Topico;
import org.example.forum.domain.usuario.Usuario;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "tbl_respuestas")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Usuario autor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Topico topico;

}
