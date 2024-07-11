package org.example.forum.service;


import org.example.forum.auth.LoginRequest;
import org.example.forum.auth.RegisterRequest;
import org.example.forum.domain.usuario.Role;
import org.example.forum.domain.usuario.Usuario;
import org.example.forum.domain.usuario.UsuarioRepository;
import org.example.forum.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String loginUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );

        return jwtTokenProvider.generateToken(authentication);
    }

    public ResponseEntity<?> registerUser(RegisterRequest registerRequest) {

        if (usuarioRepository.existsByEmail(registerRequest.email())) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        if (usuarioRepository.existsByUsername(registerRequest.username())) {
            return ResponseEntity.badRequest().body("Username already taken");
        }
        Usuario newUsuario = new Usuario();
        newUsuario.setEmail(registerRequest.email());
        newUsuario.setPassword(passwordEncoder.encode(registerRequest.password()));
        newUsuario.setUsername(registerRequest.username());
        newUsuario.setRole(Role.USER);
        newUsuario.setEnabled(true);

        usuarioRepository.save(newUsuario);

        return ResponseEntity.ok("Usuario Guardado Correctamente");
    }
}
