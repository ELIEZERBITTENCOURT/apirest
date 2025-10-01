package br.com.treinamento.apirest.core.service.jwt;

import java.time.Instant;
import java.util.Date;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import br.com.treinamento.apirest.config.JwtConfigProperties;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JjwtJwtService implements JwtService {
    
    private final JwtConfigProperties jwtConfigProperties;

    @Override
    public String generateToken(String sub) {
        var now = Instant.now();
        var expiration = now.plus(jwtConfigProperties.getExpiration());
        var key = Keys.hmacShaKeyFor(jwtConfigProperties.getSecret().getBytes());
        return Jwts.builder()
                .subject(sub)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .signWith(key)
                .compact();
    }

    @Override
    public String getSubFromToken(String token) {
        var key = Keys.hmacShaKeyFor(jwtConfigProperties.getSecret().getBytes());
        try{
            return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        }catch (JwtException e) {
            throw new JwtServiceException(e.getLocalizedMessage());
        }

    }
}