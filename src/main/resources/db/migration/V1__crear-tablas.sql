CREATE TABLE tbl_usuarios (
                              id BIGSERIAL PRIMARY KEY,
                              username VARCHAR(100) NOT NULL,
                              email VARCHAR(200) NOT NULL,
                              password VARCHAR(255) NOT NULL,
                              activo BOOLEAN,
                              role VARCHAR(5) NOT NULL CHECK (role IN ('ADMIN', 'USER'))
);

CREATE TABLE tbl_cursos (
                            id BIGSERIAL PRIMARY KEY,
                            nombre VARCHAR(100) NOT NULL,
                            descripcion TEXT
);

CREATE TABLE tbl_topicos (
                             id BIGSERIAL PRIMARY KEY,
                             titulo VARCHAR(200) NOT NULL,
                             mensaje TEXT,
                             fecha_creacion TIMESTAMP,
                             fecha_modificacion TIMESTAMP,
                             status VARCHAR(7) NOT NULL CHECK (status IN ('ABIERTO', 'CERRADO')),
                             usuario_id BIGINT,
                             curso_id BIGINT,
                             CONSTRAINT fk_topicos_usuario_id FOREIGN KEY (usuario_id) REFERENCES tbl_usuarios (id),
                             CONSTRAINT fk_topicos_curso_id FOREIGN KEY (curso_id) REFERENCES tbl_cursos (id)
);

CREATE TABLE tbl_respuestas (
                                id BIGSERIAL PRIMARY KEY,
                                mensaje TEXT,
                                fecha_creacion TIMESTAMP,
                                fecha_modificacion TIMESTAMP,
                                autor_id BIGINT,
                                topico_id BIGINT,
                                CONSTRAINT fk_respuestas_autor_id FOREIGN KEY (autor_id) REFERENCES tbl_usuarios (id),
                                CONSTRAINT fk_respuestas_topico_id FOREIGN KEY (topico_id) REFERENCES tbl_topicos (id)
);