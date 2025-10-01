package br.com.treinamento.apirest.api.jobs.mappers;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.treinamento.apirest.api.jobs.dtos.JobRequest;
import br.com.treinamento.apirest.api.jobs.dtos.JobResponse;
import br.com.treinamento.apirest.core.models.Job;

@Component
public class JobMapperLocal implements JobMapper {

    @Override
    public Job toModel(JobRequest jobRequest) {
        if (Objects.isNull(jobRequest)) {
            throw new IllegalArgumentException("jobRequest cannot be null");
        }

        return Job.builder()
            .name(jobRequest.getName())
            .description(jobRequest.getDescription())
            .location(jobRequest.getLocation())
            .type(jobRequest.getType())
            .level(jobRequest.getLevel())
            .salary(jobRequest.getSalary())
            .build();
    }

    @Override
    public JobResponse toResponse(Job job) {
        if (Objects.isNull(job)) {
            throw new IllegalArgumentException("job cannot be null");
        }

        var skills = job.getSkills()
            .stream()
            .map(skill -> skill.getName())
            .toList();

        return JobResponse.builder()
            .id(job.getId())
            .name(job.getName())
            .description(job.getDescription())
            .location(job.getLocation())
            .type(job.getType())
            .level(job.getLevel())
            .salary(job.getSalary())
            .skills(skills)
            .companyId(job.getCompany().getId())
            .build();
    }
    
}