package br.com.treinamento.apirest.api.skills.mappers;

import java.util.Objects;
import org.springframework.stereotype.Component;

import br.com.treinamento.apirest.api.skills.dtos.SkillResponse;
import br.com.treinamento.apirest.core.models.Skill;

@Component
public class SkillMapperLocal implements SkillMapper {
    
    @Override
    public SkillResponse toResponse(Skill skill) {
        if (Objects.isNull(skill)) {
            throw new IllegalArgumentException("skill cannot be null");
        }

        return  new SkillResponse(skill.getId(), skill.getName());
    }
}