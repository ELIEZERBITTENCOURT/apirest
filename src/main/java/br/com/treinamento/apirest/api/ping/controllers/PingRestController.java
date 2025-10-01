package br.com.treinamento.apirest.api.ping.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinamento.apirest.api.ping.services.PingService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ping")
public class PingRestController {

    private final PingService pingService;

    @GetMapping
    public Map<String, String> ping() {
        return pingService.ping();
    }
    
}
