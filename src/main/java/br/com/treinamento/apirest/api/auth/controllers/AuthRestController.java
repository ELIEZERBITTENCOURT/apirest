package br.com.treinamento.apirest.api.auth.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.treinamento.apirest.api.auth.dtos.LoginRequest;
import br.com.treinamento.apirest.api.auth.dtos.MeResponse;
import br.com.treinamento.apirest.api.auth.dtos.TokenResponse;
import br.com.treinamento.apirest.api.auth.services.AuthService;
import br.com.treinamento.apirest.core.permissions.JobsPermissions;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthRestController {
    
    private final AuthService authService;

    @PostMappping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/me")
    @JobsPermissions.IsAuthenticated
    public MeResponse me() {
        return authService.me();
    }
}