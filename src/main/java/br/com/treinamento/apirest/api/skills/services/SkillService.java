package br.com.treinamento.apirest.api.skills.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import br.com.treinamento.apirest.api.skills.dtos.SkillResponse;
import br.com.treinamento.apirest.api.skills.mappers.SkillMapper;
import br.com.treinamento.apirest.core.exceptions.skill.SkillNotFoundException;
import br.com.treinamento.apirest.core.repositories.SkillRepository;

@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillMapper skillMapper;
    private final SkillRepository skillRepository;

    public List<SkillResponse> findAll(Sort sort) {
        return skillRepository.findAll(sort)
            .stream()
            .map(skillMapper::toResponse)
            .toList();
    }

    public SkillResponse findById(Long id) {
        return skillRepository.findById(id)
            .map(skillMapper::toResponse)
            .orElseThrow(SkillNotFoundException::new);
    }
}
