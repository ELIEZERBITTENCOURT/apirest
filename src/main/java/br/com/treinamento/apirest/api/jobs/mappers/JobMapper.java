package br.com.treinamento.apirest.api.jobs.mappers;

import br.com.treinamento.apirest.api.jobs.dtos.JobResponse;
import br.com.treinamento.apirest.api.jobs.dtos.JobRequest;
import br.com.treinamento.apirest.core.models.Job;

public interface JobMapper {

    Job toModel(JobRequest jobRequest);
    JobResponse toResponse(Job job);

}
