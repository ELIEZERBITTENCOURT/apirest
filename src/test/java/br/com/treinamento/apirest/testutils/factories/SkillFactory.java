package br.com.treinamento.apirest.testutils.factories;

import java.util.List;
import br.com.treinamento.apirest.core.models.Skill;

public class SkillFactory {
    public static Skill createJava() {
        return new Skill(1L, "Java");
    }

    public static Skill createSpring() {
        return new Skill(2L, "Spring");
    }

    public static List<Skill> createSkills() {
        return List.of(createJava(), createSpring());
    }
}
