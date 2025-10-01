package br.com.treinamento.apirest.api.candidates.mappers;

import java.util.Objects;
import org.springframework.stereotype.Component;

import br.com.treinamento.apirest.api.candidates.dtos.CandidateRequest;
import br.com.treinamento.apirest.api.candidates.dtos.CandidateResponse;
import br.com.treinamento.apirest.core.models.Candidate;

@Component
public class CandidateMapperLocal implements CandidateMapper {

    @Override
    public CandidateResponse toResponse(Candidate candidate) {
        if (Objects.isNull(candidate)) {
            throw new IllegalArgumentException("candidate cannot be null");
        }
        return CandidateResponse.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .description(candidate.getDescription())
                .email(candidate.getEmail())
                .linkedin(candidate.getLinkedin())
                .github(candidate.getGithub())
                .build();
    }

    @Override
    public Candidate toModel(CandidateRequest candidateRequest) {
        if (Objects.isNull(candidateRequest)) {
            throw new IllegalArgumentException("candidateRequest cannot be null");
        }
        return Candidate.builder()
                .name(candidateRequest.getName())
                .description(candidateRequest.getDescription())
                .email(candidateRequest.getEmail())
                .password(candidateRequest.getPassword())
                .linkedin(candidateRequest.getLinkedin())
                .github(candidateRequest.getGithub())
                .build();
    }
}