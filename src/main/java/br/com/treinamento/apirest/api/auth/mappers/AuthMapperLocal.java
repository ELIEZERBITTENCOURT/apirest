package br.com.treinamento.apirest.api.auth.mappers;

import java.util.Objects;
import org.springframework.stereotype.Component;
import br.com.treinamento.apirest.api.auth.dtos.MeCandidateResponse;
import br.com.treinamento.apirest.api.auth.dtos.MeCompanyResponse;
import br.com.treinamento.apirest.core.models.Candidate;
import br.com.treinamento.apirest.core.models.Company;

@Component
public class AuthMapperLocal implements AuthMapper {
    @Override
    public MeCompanyResponse toMeCompanyResponse(Company company) {
        if (Objects.isNull(company)){
            throw new IllegalArgumentException("Company cannot be null");
        }

        return MeCompanyResponse.builder()
            .id(company.getId())
            .name(company.getName())
            .website(company.getWebsite())
            .description(company.getDescription())
            .email(company.getEmail())
            .build();
    }

    @Override
    public MeCandidateResponse toMeCandidateResponse(Candidate candidate) {
        if (Objects.isNull(candidate)){
            throw new IllegalArgumentException("Candidate cannot be null");
        }

        return MeCandidateResponse.builder()
            .id(candidate.getId())
            .name(candidate.getName())
            .description(candidate.getDescription())
            .email(candidate.getEmail())
            .linkedin(candidate.getLinkedin())
            .github(candidate.getGithub())
            .build();
    }
}