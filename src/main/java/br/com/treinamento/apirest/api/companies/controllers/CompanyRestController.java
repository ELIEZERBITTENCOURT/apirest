package br.com.treinamento.apirest.api.companies.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinamento.apirest.api.companies.dtos.CompanyRequest;
import br.com.treinamento.apirest.api.companies.dtos.CompanyResponse;
import br.com.treinamento.apirest.api.companies.services.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
public class CompanyRestController {

    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CompanyResponse create(@RequestBody @Valid CompanyRequest companyRequest) {
        return companyService.create(companyRequest);
    }

    @GetMapping({"/{id}"})
    public CompanyResponse findById(@PathVariable UUID id) {
        return companyService.findById(id);
    }

}
