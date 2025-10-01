package br.com.treinamento.apirest.core.exceptions.skill;

import br.com.treinamento.apirest.core.exceptions.ModelNotFoundException;

public class SkillNotFoundException extends ModelNotFoundException {
    
    public SkillNotFoundException() {
        super("Skill not found");
    }

    public SkillNotFoundException(String message) {
        super(message);
    }
}
