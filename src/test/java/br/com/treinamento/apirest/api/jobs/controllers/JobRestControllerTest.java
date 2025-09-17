package br.com.treinamento.apirest.api.jobs.controllers;

import br.com.treinamento.apirest.api.jobs.assemblers.JobAssembler;
import br.com.treinamento.apirest.api.jobs.dtos.JobRequest;
import br.com.treinamento.apirest.api.jobs.dtos.JobResponse;
import br.com.treinamento.apirest.api.jobs.mappers.JobMapper;
import br.com.treinamento.apirest.core.enums.JobLevel;
import br.com.treinamento.apirest.core.enums.JobType;
import br.com.treinamento.apirest.core.models.Job;
import br.com.treinamento.apirest.core.repositories.JobRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobRestController.class)
@ExtendWith(MockitoExtension.class)
class JobRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private JobRepository jobRepository;

    @MockitoBean
    private JobMapper jobMapper;

    @MockitoBean
    private JobAssembler jobAssembler;

    @MockitoBean
    private org.springframework.data.web.PagedResourcesAssembler<JobResponse> pagedResourcesAssembler;

    private JobRequest buildValidJobRequest() {
        return JobRequest.builder()
                .title("Desenvolvedor Java")
                .description("Desenvolvimento de APIs com Spring Boot")
                .company("Tech Company")
                .location("São Paulo")
                .type(JobType.FULL_TIME)
                .level(JobLevel.SENIOR)
                .salary(new BigDecimal("12000.00"))
                .skills(List.of(1L, 2L))
                .build();
    }

    private Job buildJobEntity() {
        return Job.builder()
                .id(1L)
                .title("Desenvolvedor Java")
                .description("Desenvolvimento de APIs com Spring Boot")
                .company("Tech Company")
                .location("São Paulo")
                .type(JobType.FULL_TIME)
                .level(JobLevel.SENIOR)
                .salary(new BigDecimal("12000.00"))
                .skills(List.of())
                .build();
    }

    private JobResponse buildJobResponse() {
        return JobResponse.builder()
                .id(1L)
                .title("Desenvolvedor Java")
                .description("Desenvolvimento de APIs com Spring Boot")
                .company("Tech Company")
                .location("São Paulo")
                .type(JobType.FULL_TIME)
                .level(JobLevel.SENIOR)
                .salary(new BigDecimal("12000.00"))
                .build();
    }

    @Test
    void deveCriarJobComSucesso() throws Exception {
        JobRequest request = buildValidJobRequest();
        Job job = buildJobEntity();
        JobResponse response = buildJobResponse();

        when(jobMapper.toJob(request)).thenReturn(job);
        when(jobRepository.save(job)).thenReturn(job);
        when(jobMapper.toJobResponse(job)).thenReturn(response);
        when(jobAssembler.toModel(response)).thenReturn(EntityModel.of(response));

        mockMvc.perform(post("/api/jobs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Desenvolvedor Java"))
                .andExpect(jsonPath("$.salary").value(12000.00));
    }

    @Test
    void deveRetornar400QuandoJobRequestInvalido() throws Exception {
        JobRequest invalidRequest = JobRequest.builder()
                .title("") // inválido
                .description("curto") // inválido
                .company("")
                .location("")
                .type(null)
                .level(null)
                .salary(BigDecimal.ZERO)
                .skills(List.of())
                .build();

        mockMvc.perform(post("/api/jobs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation error"))
                .andExpect(jsonPath("$.errors.title").isArray())
                .andExpect(jsonPath("$.errors.salary").isArray());
    }

    @Test
    void deveBuscarJobPorId() throws Exception {
        Job job = buildJobEntity();
        JobResponse response = buildJobResponse();

        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));
        when(jobMapper.toJobResponse(job)).thenReturn(response);
        when(jobAssembler.toModel(response)).thenReturn(EntityModel.of(response));

        mockMvc.perform(get("/api/jobs/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Desenvolvedor Java"))
                .andExpect(jsonPath("$.company").value("Tech Company"));
    }

    @Test
    void deveRetornar404QuandoJobNaoEncontrado() throws Exception {
        when(jobRepository.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/jobs/{id}", 99L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Job not found"));
    }

    @Test
    void deveAtualizarJob() throws Exception {
        Job job = buildJobEntity();
        JobRequest updateRequest = JobRequest.builder()
                .title("Dev Backend Senior")
                .description("API REST avançada")
                .company("Tech Company")
                .location("São Paulo")
                .type(JobType.FULL_TIME)
                .level(JobLevel.SENIOR)
                .salary(BigDecimal.valueOf(15000))
                .skills(List.of(1L, 2L))
                .build();

        Job updatedJob = Job.builder()
                .id(1L)
                .title("Dev Backend Senior")
                .description("API REST avançada")
                .company("Tech Company")
                .location("São Paulo")
                .type(JobType.FULL_TIME)
                .level(JobLevel.SENIOR)
                .salary(BigDecimal.valueOf(15000))
                .skills(List.of())
                .build();

        JobResponse updatedResponse = JobResponse.builder()
                .id(1L)
                .title("Dev Backend Senior")
                .description("API REST avançada")
                .company("Tech Company")
                .location("São Paulo")
                .type(JobType.FULL_TIME)
                .level(JobLevel.SENIOR)
                .salary(BigDecimal.valueOf(15000))
                .build();

        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));
        when(jobMapper.toJob(updateRequest)).thenReturn(updatedJob);
        when(jobRepository.save(any(Job.class))).thenReturn(updatedJob);
        when(jobMapper.toJobResponse(updatedJob)).thenReturn(updatedResponse);
        when(jobAssembler.toModel(updatedResponse)).thenReturn(EntityModel.of(updatedResponse));

        mockMvc.perform(put("/api/jobs/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Dev Backend Senior"))
                .andExpect(jsonPath("$.level").value("SENIOR"))
                .andExpect(jsonPath("$.salary").value(15000));
    }

    @Test
    void deveDeletarJob() throws Exception {
        Job job = buildJobEntity();

        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));

        mockMvc.perform(delete("/api/jobs/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(jobRepository, times(1)).delete(job);
    }
}
