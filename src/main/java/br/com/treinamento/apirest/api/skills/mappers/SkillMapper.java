package br.com.treinamento.apirest.api.skills.mappers;

import br.com.treinamento.apirest.api.skills.dtos.SkillResponse;
import br.com.treinamento.apirest.api.skills.dtos.SkillRequest;
import br.com.treinamento.apirest.core.models.Skill;

public interface SkillMapper{
    Skill toSkill(SkillRequest skillRequest);
    SkillResponse toSkillResponse(Skill skill);
}