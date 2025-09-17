package br.com.treinamento.apirest.api.skills.controllers;

import br.com.treinamento.apirest.api.skills.assemblers.SkillAssembler;
import br.com.treinamento.apirest.api.skills.dtos.SkillRequest;
import br.com.treinamento.apirest.api.skills.dtos.SkillResponse;
import br.com.treinamento.apirest.api.skills.mappers.SkillMapper;
import br.com.treinamento.apirest.core.models.Skill;
import br.com.treinamento.apirest.core.repositories.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SkillRestController.class)
class SkillRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private SkillRepository skillRepository;

    @MockitoBean
    private SkillMapper skillMapper;

    @MockitoBean
    private SkillAssembler skillAssembler;

    @MockitoBean
    private PagedResourcesAssembler<SkillResponse> pagedResourcesAssembler;

    @MockitoBean
    private br.com.treinamento.apirest.core.service.http.HttpService httpService;

    @BeforeEach
    void setup() {
        Mockito.reset(skillRepository, skillMapper, skillAssembler, pagedResourcesAssembler);
    }

    private SkillRequest buildValidSkillRequest() {
        return new SkillRequest("Java");
    }

    private Skill buildSkill() {
        return new Skill(1L, "Java");
    }

    private SkillResponse buildSkillResponse() {
        return new SkillResponse(1L, "Java");
    }

    @Test
    void deveCriarSkillComSucesso() throws Exception {
        SkillRequest request = buildValidSkillRequest();
        Skill skill = buildSkill();
        SkillResponse response = buildSkillResponse();

        when(skillMapper.toSkill(request)).thenReturn(skill);
        when(skillRepository.save(skill)).thenReturn(skill);
        when(skillMapper.toSkillResponse(skill)).thenReturn(response);
        when(skillAssembler.toModel(response)).thenReturn(EntityModel.of(response));

        mockMvc.perform(post("/api/skills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Java"));
    }

    @Test
    void deveRetornar400QuandoSkillRequestInvalido() throws Exception {
        SkillRequest request = new SkillRequest(""); // nome vazio

        mockMvc.perform(post("/api/skills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.name").exists());
    }

    @Test
    void deveBuscarSkillPorId() throws Exception {
        Skill skill = buildSkill();
        SkillResponse response = buildSkillResponse();

        when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));
        when(skillMapper.toSkillResponse(skill)).thenReturn(response);
        when(skillAssembler.toModel(response)).thenReturn(EntityModel.of(response));

        mockMvc.perform(get("/api/skills/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Java"));
    }

    @Test
    void deveRetornar404QuandoSkillNaoEncontrada() throws Exception {
        when(skillRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/skills/{id}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Skill not found"));
    }

    @Test
    void deveAtualizarSkill() throws Exception {
        Skill skill = buildSkill();
        SkillRequest updateRequest = new SkillRequest("Java Avançado");
        SkillResponse response = new SkillResponse(1L, "Java Avançado");

        when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));
        when(skillRepository.save(any(Skill.class))).thenReturn(skill);
        when(skillMapper.toSkillResponse(skill)).thenReturn(response);
        when(skillAssembler.toModel(response)).thenReturn(EntityModel.of(response));

        mockMvc.perform(put("/api/skills/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Java Avançado"));
    }

    @Test
    void deveDeletarSkill() throws Exception {
        Skill skill = buildSkill();

        when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));

        mockMvc.perform(delete("/api/skills/{id}", 1L))
                .andExpect(status().isNoContent());
    }

}
