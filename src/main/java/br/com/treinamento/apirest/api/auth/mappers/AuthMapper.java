package br.com.treinamento.apirest.api.auth.mappers;

import br.com.treinamento.apirest.api.auth.dtos.MeCandidateResponse;
import br.com.treinamento.apirest.api.auth.dtos.MeCompanyResponse;
import br.com.treinamento.apirest.core.models.Candidate;
import br.com.treinamento.apirest.core.models.Company;

public interface AuthMapper {
    MeCompanyResponse toMeCompanyResponse(Company company);
    MeCandidateResponse toMeCandidateResponse(Candidate candidate);
}