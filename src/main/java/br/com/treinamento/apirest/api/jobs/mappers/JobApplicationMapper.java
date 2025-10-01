package br.com.treinamento.apirest.api.jobs.mappers;

import br.com.treinamento.apirest.api.jobs.dtos.JobApplicationResponse;
import br.com.treinamento.apirest.core.models.Candidate;

public interface JobApplicationMapper {
    JobApplicationResponse toResponse(Candidate candidate);
}