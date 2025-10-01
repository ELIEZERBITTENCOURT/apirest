package br.com.treinamento.apirest.core.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinamento.apirest.core.models.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findAllByNameIn(Iterable<String> names);
}
