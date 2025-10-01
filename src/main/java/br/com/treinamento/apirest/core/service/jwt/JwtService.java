package br.com.treinamento.apirest.core.service.jwt;

public interface JwtService {
    String generateToken(String sub);
    String getSubFromToken(String token);
}