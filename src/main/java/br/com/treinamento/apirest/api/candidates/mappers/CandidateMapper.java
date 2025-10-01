package br.com.treinamento.apirest.api.candidates.mappers;

import br.com.treinamento.apirest.api.candidates.dtos.CandidateRequest;
import br.com.treinamento.apirest.api.candidates.dtos.CandidateResponse;
import br.com.treinamento.apirest.core.models.Candidate;

public interface CandidateMapper {
    CandidateResponse toResponse(Candidate candidate);
    Candidate toModel(CandidateRequest candidateRequest);
}