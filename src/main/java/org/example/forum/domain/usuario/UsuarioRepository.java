package org.example.forum.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    Usuario findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Usuario findByUsername(String username);
}

