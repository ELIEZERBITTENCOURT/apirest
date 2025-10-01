package br.com.treinamento.apirest.api.auth.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.treinamento.apirest.api.auth.dtos.LoginRequest;
import br.com.treinamento.apirest.api.auth.dtos.MeResponse;
import br.com.treinamento.apirest.api.auth.dtos.TokenResponse;
import br.com.treinamento.apirest.api.auth.mappers.AuthMapper;
import br.com.treinamento.apirest.core.repositories.CandidateRepository;
import br.com.treinamento.apirest.core.repositories.CompanyRepository;
import br.com.treinamento.apirest.core.service.jwt.JwtService;
import br.com.treinamento.apirest.core.service.auth.SecurityService;   
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthMapper authMapper;
    private final JwtService jwtService;
    private final SecurityService securityService;
    private final CandidateRepository candidateRepository;
    private final CompanyRepository companyRepository;
    private final AuthenticationManager authenticationManager;

    public TokenResponse login(LoginRequest loginRequest) {
        var authentication = new UsernamePasswordAuthenticationToken(
            loginRequest.getEmail(), 
            loginRequest.getPassword()
        );
        authenticationManager.authenticate(authentication);
        return TokenResponse.builder()
            .token(jwtService.generateToken(loginRequest.getEmail()))
            .build();
    }

    public MeResponse me(){
        var authException = new UsernameNotFoundException("Usuário não autenticado");
        var user = securityService.getAuthenticatedUser();

        if(user.getRole().equals("COMPANY")) {
            return companyRepository.findByEmail(user.getUsername())
                .map(authMapper::toMeCompanyResponse)
                .orElseThrow(() -> authException);
        }
        return candidateRepository.findByEmail(user.getUsername())
            .map(authMapper::toMeCandidateResponse)
            .orElseThrow(() -> authException);
    }

    
}