package org.example.forum.security;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import org.example.forum.exception.TokenExpiredException;
import org.example.forum.exception.TokenMalformedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private SecretKey key;

    private int jwtExpirationInMs=300000;
    @PostConstruct
    public void init() {
        key = Jwts.SIG.HS512.key().build();
    }
    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        String role = userDetails.getAuthorities().stream().findFirst().get().toString();


        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key).build()
                .parseSignedClaims(token)
                .getPayload();
             return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(authToken);
            return true;
        }

        catch (MalformedJwtException ex) {
            throw new TokenMalformedException("Token JWT no valida");
        }
        catch (ExpiredJwtException ex) {
            throw new TokenExpiredException("Token JWT caducado");
        }

        catch (JwtException | IllegalArgumentException ex) {
            throw new BadCredentialsException("Invalid JWT");
        }

    }
}
