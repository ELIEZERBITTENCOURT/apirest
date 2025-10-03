package br.com.treinamento.apirest.api.companies.services;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.treinamento.apirest.api.companies.dtos.CompanyRequest;
import br.com.treinamento.apirest.api.companies.dtos.CompanyResponse;
import br.com.treinamento.apirest.api.companies.mappers.CompanyMapper;
import br.com.treinamento.apirest.core.exceptions.company.CompanyEmailAlreadyInUseException;
import br.com.treinamento.apirest.core.exceptions.company.CompanyNameAlreadyInUseException;
import br.com.treinamento.apirest.core.exceptions.company.CompanyNotFoundException;
import br.com.treinamento.apirest.core.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyMapper companyMapper;
    private final PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;

    public CompanyResponse create(CompanyRequest companyRequest) {
        if (companyRepository.existsByEmail(companyRequest.getEmail())) {
            throw new CompanyEmailAlreadyInUseException();
        }
        if (companyRepository.existsByName(companyRequest.getName())) {
            throw new CompanyNameAlreadyInUseException();
        }

        var company = companyMapper.toModel(companyRequest);
        company.setPassword(passwordEncoder.encode(companyRequest.getPassword()));
        var companySaved = companyRepository.save(company);
        return companyMapper.toResponse(companySaved);
    }

    public CompanyResponse findById(UUID id) {
        return companyRepository.findById(id)
            .map(companyMapper::toResponse)
            .orElseThrow(CompanyNotFoundException::new);
    }
    
}