package br.com.treinamento.apirest.api.companies.mappers;

import br.com.treinamento.apirest.api.companies.dtos.CompanyRequest;
import br.com.treinamento.apirest.api.companies.dtos.CompanyResponse;
import br.com.treinamento.apirest.core.models.Company;

public interface CompanyMapper {

    CompanyResponse toResponse(Company company);
    Company toModel(CompanyRequest companyRequest);
    
}