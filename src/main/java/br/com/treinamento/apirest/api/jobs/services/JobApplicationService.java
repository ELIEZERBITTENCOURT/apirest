package br.com.treinamento.apirest.api.jobs.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.treinamento.apirest.api.jobs.dtos.JobApplicationResponse;
import br.com.treinamento.apirest.api.jobs.mappers.JobApplicationMapper;
import br.com.treinamento.apirest.core.exceptions.candidate.CandidateNotFoundException;
import br.com.treinamento.apirest.core.exceptions.job.JobNotFoundException;
import br.com.treinamento.apirest.core.repositories.CandidateRepository;
import br.com.treinamento.apirest.core.repositories.JobRepository;
import br.com.treinamento.apirest.core.service.auth.SecurityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobRepository jobRepository;
    private final SecurityService securityService;
    private final CandidateRepository candidateRepository;
    private final JobApplicationMapper jobApplicationMapper;

    public void create(UUID id) {
        var job = jobRepository.findById(id).orElseThrow(JobNotFoundException::new);
        var candidateId = securityService.getAuthenticatedUser().getId();
        if (jobRepository.existsByIdAndCandidatesId(id, candidateId)) {
            return;
        }
        var candidate = candidateRepository.findById(candidateId).orElseThrow(CandidateNotFoundException::new);
        job.getCandidates().add(candidate);
        jobRepository.save(job);
    }

    public List<JobApplicationResponse> findAll(UUID id) {
        return jobRepository.findById(id)
            .orElseThrow(JobNotFoundException::new)
            .getCandidates()
            .stream()
            .map(jobApplicationMapper::toResponse)
            .toList();
    }
    
}