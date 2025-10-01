package br.com.treinamento.apirest.api.skills.mappers;

import br.com.treinamento.apirest.api.skills.dtos.SkillResponse;
import br.com.treinamento.apirest.core.models.Skill;

public interface SkillMapper{
    SkillResponse toResponse(Skill skill);
}