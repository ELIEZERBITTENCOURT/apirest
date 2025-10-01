package br.com.treinamento.apirest.core.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinamento.apirest.core.models.Job;

public interface JobRepository extends JpaRepository<Job, UUID> {
    @EntityGraph(attributePaths = {"company","skills"})
    List<Job> findAll();

    boolean existsByIdAndCompanyId(UUID id, UUID companyId);
    boolean existsByIdAndCandidatesId(UUID id, UUID candidateId);
}
