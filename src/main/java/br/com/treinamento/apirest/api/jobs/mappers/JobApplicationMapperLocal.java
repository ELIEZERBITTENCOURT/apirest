package br.com.treinamento.apirest.api.jobs.mappers;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.treinamento.apirest.api.jobs.dtos.JobApplicationResponse;
import br.com.treinamento.apirest.core.models.Candidate;

@Component
public class JobApplicationMapperLocal implements JobApplicationMapper {

    @Override
    public JobApplicationResponse toResponse(Candidate candidate) {
        if (Objects.isNull(candidate)) {
            throw new IllegalArgumentException("canidate cannot be null");
        }

        return JobApplicationResponse.builder()
            .id(candidate.getId())
            .name(candidate.getName())
            .description(candidate.getDescription())
            .email(candidate.getEmail())
            .github(candidate.getGithub())
            .linkedin(candidate.getLinkedin())
            .build();
    }
    
}